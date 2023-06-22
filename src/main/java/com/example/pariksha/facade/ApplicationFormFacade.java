package com.example.pariksha.facade;


import com.example.pariksha.dto.*;
import com.example.pariksha.model.ApplicationForm;
import com.example.pariksha.model.VacancyCategoryWise;
import com.example.pariksha.service.ApplicationFormDataService;
import com.example.pariksha.service.ApplicationFormService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ApplicationFormDTO getFormDetails(String examName){
        ApplicationFormDTO applicationFormDto = new ApplicationFormDTO();
        ApplicationForm applicationForm = applicationFormDataService.fetchUsingExamId(examName).get();
        if(applicationForm != null) {
           applicationFormDto = new Gson().fromJson(new Gson().toJson(applicationForm), ApplicationFormDTO.class);
            return applicationFormDto;
        }
        return null;
    }

    public boolean saveApplicationForm(ApplicationFormDTO applicationFormDto){
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

    public List<ApplicationFormDTO> getAllData(){
        return applicationFormService.getAllAppData();
    }
    public List<ApplicationForm> getLastData(int formCount){
        return applicationFormDataService.getAllLimited(formCount).get();
    }

    public List<ApplicationForm> getLatestData(int formCount){
        return applicationFormDataService.getAllLatestData(formCount).get();
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
        ApplicationFormDTO applicationFormDto = applicationFormDataService.getApplicationFormDtoForExamId(eligibilityCheckRequestDto.getExamId());
        try{
            eligibilityResponseDto = applicationFormService.checkEligibility(eligibilityCheckRequestDto,applicationFormDto);
        }catch(Exception e){
            System.out.println("Exception while checking eligibility");
        }
        return eligibilityResponseDto;
    }

    public boolean saveVacancyDetails(VacancyCategoryWiseDto vacancyCategoryWiseDto){
        try {
            VacancyCategoryWise vacancyCategoryWise = modelMapper.map(vacancyCategoryWiseDto, VacancyCategoryWise.class);
            return applicationFormDataService.saveVacancyCategoryWise(vacancyCategoryWise);
        }catch(Exception e){
            System.out.println("Exception occurred while saving");
        }
        return false;
    }
}
