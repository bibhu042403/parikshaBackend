package com.example.pariksha.service.impl;

import com.example.pariksha.constants.ParikhshaConstant;
import com.example.pariksha.dto.ApplicationFormDto;
import com.example.pariksha.dto.EligibilityCheckRequestDto;
import com.example.pariksha.dto.EligibilityResponseDto;
import com.example.pariksha.dto.VacancyCategoryWiseDto;
import com.example.pariksha.service.ApplicationFormDataService;
import com.example.pariksha.service.ApplicationFormService;
import com.example.pariksha.utlis.ParikshaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ApplicationFormServiceImpl implements ApplicationFormService {

    @Autowired
    ApplicationFormDataService applicationFormDataService;

    @Override
    public EligibilityResponseDto checkEligibility(EligibilityCheckRequestDto eligibilityCheckRequestDto, ApplicationFormDto applicationFormDto) {
        EligibilityResponseDto eligibilityResponseDto = new EligibilityResponseDto();
        if(eligibleByAge(eligibilityCheckRequestDto,applicationFormDto)){
            int appFee = Math.min(feeBasedOnCategory(eligibilityCheckRequestDto, applicationFormDto), feeBasedOnGender(eligibilityCheckRequestDto, applicationFormDto));
            eligibilityResponseDto.setApplicationFee(String.valueOf(appFee));
            eligibilityResponseDto.setEligibility(true);
            String lstDate = applicationFormDto.getLastDate().toString();
            eligibilityResponseDto.setLastDate(lstDate);
            eligibilityResponseDto.setVacancy(String.valueOf(getVacancy(eligibilityCheckRequestDto)));
            return eligibilityResponseDto;
        }
        eligibilityResponseDto.setEligibility(false);
        return eligibilityResponseDto;
    }

    public int feeBasedOnCategory(EligibilityCheckRequestDto eligibilityCheckRequestDto, ApplicationFormDto applicationFormDto){
        if(eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.ST))
            return Integer.parseInt(applicationFormDto.getStScFee());
        else if(eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.GEN))
            return Integer.parseInt(applicationFormDto.getGeneralFee());
        else if(eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.OBC))
            return Integer.parseInt(applicationFormDto.getObcFee());
        else if(eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.SC))
            return Integer.parseInt(applicationFormDto.getStScFee());
        return 0;
    }
    public int feeBasedOnGender(EligibilityCheckRequestDto eligibilityCheckRequestDto, ApplicationFormDto applicationFormDto){
        if(eligibilityCheckRequestDto.getGender().equals(ParikhshaConstant.MALE))
            return Integer.parseInt(applicationFormDto.getGeneralFee());
        else if(eligibilityCheckRequestDto.getGender().equals(ParikhshaConstant.FEMALE))
            return Integer.parseInt(applicationFormDto.getFemaleFee());
        else if(eligibilityCheckRequestDto.getGender().equals(ParikhshaConstant.TRANS))
            return Integer.parseInt(applicationFormDto.getFemaleFee());
        return 0;
    }

    public boolean eligibleByAge(EligibilityCheckRequestDto eligibilityCheckRequestDto, ApplicationFormDto applicationFormDto){
        String dob = eligibilityCheckRequestDto.getDob();
        try {
            int age = ParikshaUtils.calculateAge(dob);
            if (eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.ST) && age < Integer.parseInt(applicationFormDto.getStScAge()))
                return true;
            else if (eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.SC) && age < Integer.parseInt(applicationFormDto.getStScAge())) {
                return true;
            } else if (eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.OBC) && age< Integer.parseInt(applicationFormDto.getObcAge())) {
                return true;
            } else if (eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.GEN) && age<Integer.parseInt(applicationFormDto.getGeneralAge())) {
                return true;
            } else if (eligibilityCheckRequestDto.getGender().equals(ParikhshaConstant.FEMALE) && age<Integer.parseInt(applicationFormDto.getFemaleAge())) {
                return true;
            } else if (eligibilityCheckRequestDto.getGender().equals(ParikhshaConstant.EX_SERVICE) && age<Integer.parseInt(applicationFormDto.getExServiceMenAge())) {
                return true;
            }
            else if (eligibilityCheckRequestDto.getGender().equals(ParikhshaConstant.PWD) && age<Integer.parseInt(applicationFormDto.getPwdObcFee())) {
                return true;
            }
    }catch(Exception e){
            System.out.println("Exception while parsing DOB");
        }
        return false;
    }

    public int getVacancy(EligibilityCheckRequestDto eligibilityCheckRequestDto){
        VacancyCategoryWiseDto vacancyCategoryWiseDto = applicationFormDataService.getVacancyDetailsForExamId(eligibilityCheckRequestDto.getExamId());
         if(eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.GEN)) {
             return vacancyCategoryWiseDto.getGeneral();
         }
         else if (eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.OBC)) {
             return vacancyCategoryWiseDto.getObc();
         } else if (eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.ST)) {
             return vacancyCategoryWiseDto.getSt();
         } else if (eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.SC)) {
             return vacancyCategoryWiseDto.getSc();
         } else if (eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.FEMALE)) {
             return vacancyCategoryWiseDto.getObcFemale();
         }
        return 0;
    }

}
