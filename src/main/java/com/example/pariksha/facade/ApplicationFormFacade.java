package com.example.pariksha.facade;


import com.example.pariksha.dto.ApplicationFormDto;
import com.example.pariksha.dto.ApplicationFormDetailsDto;
import com.example.pariksha.dto.EligibilityCheckRequestDto;
import com.example.pariksha.dto.EligibilityResponseDto;
import com.example.pariksha.model.ApplicationForm;
import com.example.pariksha.service.ApplicationFormDataService;
import com.example.pariksha.service.ApplicationFormService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ApplicationFormFacade {

    @Autowired
    ApplicationFormDataService applicationFormDataService;

    @Autowired
    ApplicationFormService applicationFormService;
    ModelMapper modelMapper = new ModelMapper();

    public ApplicationFormDto getFormDetails(String examName){
        ApplicationFormDto applicationFormDto = new ApplicationFormDto();
        ApplicationForm applicationForm = applicationFormDataService.fetchUsingExamId(examName).get();
        if(applicationForm != null) {
           applicationFormDto = new Gson().fromJson(new Gson().toJson(applicationForm), ApplicationFormDto.class);
            return applicationFormDto;
        }
        return null;
    }

    public boolean saveApplicationForm(ApplicationFormDto applicationFormDto){
        try {
            ApplicationForm applicationForm = modelMapper.map(applicationFormDto, ApplicationForm.class);
            return applicationFormDataService.saveApplicationForm(applicationForm);
        }catch(Exception e){
            System.out.println("Exception occurred while saving");
        }
       return false;
    }

    public boolean saveApplication(ApplicationForm applicationForm){
        try {
            if (applicationForm != null)
                return applicationFormDataService.saveApplicationForm(applicationForm);
        }
        catch(Exception e){
            System.out.println("Exception occur during saving" + e);
        }
        return false;
    }

    public boolean saveApplicationForm(ApplicationFormDetailsDto applicationFormDetailsDto){
                return false;
    }

    public List<ApplicationForm> getLastData(int formCount){
        List<ApplicationForm> formDetails = applicationFormDataService.getAllLimited(formCount).get();
        return formDetails;
    }

    public List<ApplicationForm> getLatestData(int formCount){
        List<ApplicationForm> formDetails = applicationFormDataService.getAllLatestData(formCount).get();
        return formDetails;
    }


    public void deleteExpiredForm(Date date){
        try {
                applicationFormDataService.deleteExpiredForm(date);
        }
        catch(Exception e) {
            System.out.println("Exception occur during deletion" + e);
        }
    }

    /**
     * Will check if user is eligible for this form or not
     * @param eligibilityCheckRequestDto will have all basic detail
     * @return age and fee details if user is eligible
     */
    public EligibilityResponseDto checkEligibility(EligibilityCheckRequestDto eligibilityCheckRequestDto){
        EligibilityResponseDto eligibilityResponseDto = new EligibilityResponseDto();
        ApplicationFormDto applicationFormDto = applicationFormDataService.getApplicationFormDtoForExamId(eligibilityCheckRequestDto.getExamId());
        try{
            eligibilityResponseDto = applicationFormService.checkEligibility(eligibilityCheckRequestDto,applicationFormDto);
        }catch(Exception e){
            System.out.println("Exception while checking eligibility");
        }
        return eligibilityResponseDto;
    }

}
