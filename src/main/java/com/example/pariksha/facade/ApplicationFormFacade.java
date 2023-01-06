package com.example.pariksha.facade;


import com.example.pariksha.dto.ApplicationFormDto;
import com.example.pariksha.dto.ApplicationFormNewDto;
import com.example.pariksha.model.ApplicationForm;
import com.example.pariksha.model.ApplicationFormDate;
import com.example.pariksha.service.ApplicationFormService;
import com.example.pariksha.utlis.ConvertionUtils;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
public class ApplicationFormFacade {

    @Autowired
    ApplicationFormService applicationFormService;

    public ApplicationFormDto getFormDetails(String examName){
        ApplicationFormDto applicationFormDto = new ApplicationFormDto();
        ApplicationForm applicationForm = applicationFormService.fetchUsingExamId(examName).get();
        if(applicationForm != null) {
           applicationFormDto = new Gson().fromJson(new Gson().toJson(applicationForm), ApplicationFormDto.class);
            return applicationFormDto;
        }
        return null;
    }

    public boolean saveApplicationForm(ApplicationFormDto applicationFormDto){
        HashSet<Boolean> check = new HashSet<>();
        try {
            if(applicationFormDto == null)
                return false;
            if (applicationFormDto.getApplicationFormDate() != null)
                check.add(applicationFormService.saveApplicationFormDate(applicationFormDto.getApplicationFormDate()));
            if(applicationFormDto.getApplicationFeeDetails() != null)
                check.add(applicationFormService.saveApplicationFeeDetails(applicationFormDto.getApplicationFeeDetails()));
            if(applicationFormDto.getApplicationAgeLimit() != null)
                check.add(applicationFormService.saveApplicationAgeLimit(applicationFormDto.getApplicationAgeLimit()));
            if(applicationFormDto.getQualificationDetails()!=null)
                check.add(applicationFormService.saveQualificationDetails(applicationFormDto.getQualificationDetails()));
            if(applicationFormDto.getVacancyCategoryWise() !=null)
                check.add(applicationFormService.saveVacancyCategoryWise(applicationFormDto.getVacancyCategoryWise()));
            if(applicationFormDto.getVacancyPostWise() !=null)
                check.add(applicationFormService.saveVacancyPostWise(applicationFormDto.getVacancyPostWise()));
            if(applicationFormDto.getApplyDetails() !=null)
                check.add(applicationFormService.saveApplyDetails(applicationFormDto.getApplyDetails()));
            if(applicationFormDto.getExamDetails() != null)
                check.add(applicationFormService.saveExamDetails(applicationFormDto.getExamDetails()));
        }
        catch(Exception e){
            System.out.println("Exception while saving" + e);
        }
        if(check.size()>1)
            return false;
        return true;
    }

    public boolean saveApplication(ApplicationForm applicationForm){
        try {
            if (applicationForm != null)
                return applicationFormService.saveApplicationForm(applicationForm);
        }
        catch(Exception e){
            System.out.println("Exception occur during saving" + e);
        }
        return false;
    }

    public boolean saveApplicationForm(ApplicationFormNewDto applicationFormNewDto){
                return false;
    }

    public List<ApplicationForm> getFiveLastData(){
        int lastData = 5;
        List<ApplicationForm> formDetails = applicationFormService.getAllLimited(lastData).get();
        return formDetails;
    }

    public List<ApplicationForm> getFiveLatestData(){
        int topData = 5;
        List<ApplicationForm> formDetails = applicationFormService.getAllLatestData(topData).get();
        return formDetails;
    }


}
