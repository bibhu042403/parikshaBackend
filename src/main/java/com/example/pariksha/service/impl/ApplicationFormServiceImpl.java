package com.example.pariksha.service.impl;

import com.example.pariksha.constants.ParikhshaConstant;
import com.example.pariksha.dto.ApplicationFormDTO;
import com.example.pariksha.dto.EligibilityCheckRequestDto;
import com.example.pariksha.dto.EligibilityResponseDto;
import com.example.pariksha.dto.VacancyCategoryWiseDto;
import com.example.pariksha.model.ApplicationForm;
import com.example.pariksha.service.ApplicationFormDataService;
import com.example.pariksha.service.ApplicationFormService;
import com.example.pariksha.utlis.ParikshaUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationFormServiceImpl implements ApplicationFormService {

    @Autowired
    ApplicationFormDataService applicationFormDataService;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public EligibilityResponseDto checkEligibility(EligibilityCheckRequestDto eligibilityCheckRequestDto, ApplicationFormDTO applicationFormDto) {
        EligibilityResponseDto eligibilityResponseDto = new EligibilityResponseDto();
        if(eligibleByAge(eligibilityCheckRequestDto,applicationFormDto)){
            int appFee = Math.min(feeBasedOnCategory(eligibilityCheckRequestDto, applicationFormDto), feeBasedOnGender(eligibilityCheckRequestDto, applicationFormDto));
            eligibilityResponseDto.setApplicationFee(String.valueOf(appFee));
            eligibilityResponseDto.setEligibility(true);
            String lstDate = applicationFormDto.getLastDate().toString();
            eligibilityResponseDto.setLastDate(lstDate);
            eligibilityResponseDto.setVacancy(getVacancy(eligibilityCheckRequestDto));
            return eligibilityResponseDto;
        }
        eligibilityResponseDto.setEligibility(false);
        return eligibilityResponseDto;
    }

    @Override
    public List<ApplicationFormDTO> getAllAppData() {
        List<ApplicationForm> allAppDataList = applicationFormDataService.getAllFormData();
        return convertInToApplicationFormDTO(allAppDataList);
    }

    public List<ApplicationFormDTO> convertInToApplicationFormDTO(List<ApplicationForm> allAppDataList){
        List<ApplicationFormDTO> dtos = new ArrayList<>();
        for (ApplicationForm entity : allAppDataList) {
          ApplicationFormDTO applicationFormDTO = modelMapper.map(entity, ApplicationFormDTO.class);
            dtos.add(applicationFormDTO);
        }
        return dtos;
    }

    public int feeBasedOnCategory(EligibilityCheckRequestDto eligibilityCheckRequestDto, ApplicationFormDTO applicationFormDto){
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
    public int feeBasedOnGender(EligibilityCheckRequestDto eligibilityCheckRequestDto, ApplicationFormDTO applicationFormDto){
        if(eligibilityCheckRequestDto.getGender().equals(ParikhshaConstant.MALE))
            return Integer.parseInt(applicationFormDto.getGeneralFee());
        else if(eligibilityCheckRequestDto.getGender().equals(ParikhshaConstant.FEMALE))
            return Integer.parseInt(applicationFormDto.getFemaleFee());
        else if(eligibilityCheckRequestDto.getGender().equals(ParikhshaConstant.TRANS))
            return Integer.parseInt(applicationFormDto.getFemaleFee());
        return 0;
    }

    public boolean eligibleByAge(EligibilityCheckRequestDto eligibilityCheckRequestDto, ApplicationFormDTO applicationFormDto){
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

    public String getVacancy(EligibilityCheckRequestDto eligibilityCheckRequestDto){
        VacancyCategoryWiseDto vacancyCategoryWiseDto;
        String respose="";
        try {
            vacancyCategoryWiseDto = applicationFormDataService.getVacancyDetailsForExamId(eligibilityCheckRequestDto.getExamId());
            if (eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.GEN)) {
                return vacancyCategoryWiseDto.getGeneral();
            } else if (eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.OBC)) {
                return vacancyCategoryWiseDto.getObc();
            } else if (eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.ST)) {
                return vacancyCategoryWiseDto.getSt();
            } else if (eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.SC)) {
                return vacancyCategoryWiseDto.getSc();
            } else if (eligibilityCheckRequestDto.getCategory().equals(ParikhshaConstant.FEMALE)) {
                return vacancyCategoryWiseDto.getObcFemale();
            }
            respose = "No Record Found";
        }
         catch(Exception e){
             System.out.println("Exception occurred while fetching vacancy details");
             respose = "No Record Found";
            }
        return respose;
    }

}
