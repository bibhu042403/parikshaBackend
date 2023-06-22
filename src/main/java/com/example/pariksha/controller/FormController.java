package com.example.pariksha.controller;

import com.example.pariksha.dto.ApplicationFormDTO;
import com.example.pariksha.dto.EligibilityCheckRequestDto;
import com.example.pariksha.dto.EligibilityResponseDto;
import com.example.pariksha.dto.VacancyCategoryWiseDto;
import com.example.pariksha.facade.ApplicationFormFacade;
import com.example.pariksha.model.ApplicationForm;
import com.example.pariksha.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path= "/form")
public class FormController {
    @Autowired
    ApplicationFormFacade applicationFormFacade;

    @CrossOrigin
    @PostMapping(path = "/saveForms")
    public ResponseEntity<String> saveApplicationForm(@RequestBody ApplicationFormDTO applicationFormDto){
        if(applicationFormFacade.saveApplicationForm(applicationFormDto)){
            return new ResponseEntity<>("lead saved", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Lead failed to save", HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @CrossOrigin
//    @GetMapping(path = "/getAllData", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Page<ApplicationForm> getApplicationForms(
//            @RequestParam("page") int page,
//            @RequestParam("pageSize") int pageSize
//    )  {
//        int offset = (page - 1) * pageSize;
//        List<ApplicationForm> list = applicationFormFacade.getDataSubset(offset, pageSize);
//        return list;
//    }

    @CrossOrigin
    @GetMapping(path = "/getAllData", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ApplicationFormDTO> getAllData(){
        return applicationFormFacade.getAllData();
    }
    @CrossOrigin
    @GetMapping(path = "/getLastData", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ApplicationForm> getLastData(@RequestParam int formCount){
        List<ApplicationForm> list = applicationFormFacade.getLastData(formCount);
        return list;
    }

    @CrossOrigin
    @GetMapping(path = "/getLatestData")
    public List<ApplicationForm> getLatestData(@RequestParam int formCount){
        return applicationFormFacade.getLatestData(formCount);
    }

    @CrossOrigin
    @GetMapping(path = "/deleteExpiredForm")
    public ResponseEntity<String> deleteExpiredForm(){
        Date date = new Date();
        try{
            applicationFormFacade.deleteExpiredForm(date);
        }catch(Exception e){
            return new ResponseEntity<>("Form details deletion failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        return new ResponseEntity<>("form detail deleted successfully!", HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(path = "/checkEligibility")
    public Response<EligibilityResponseDto> checkEligibility(@RequestBody EligibilityCheckRequestDto eligibilityCheckRequestDto){
        Response<EligibilityResponseDto> eligibilityResponseDtoResponse = new Response<>();
        EligibilityResponseDto eligibilityResponseDto = null;
        try{
            eligibilityResponseDto = applicationFormFacade.checkEligibility(eligibilityCheckRequestDto);
            eligibilityResponseDtoResponse.setBody(eligibilityResponseDto);
            eligibilityResponseDtoResponse.setSuccess(true);
            eligibilityResponseDtoResponse.setMessage("Eligibility check done");
        }catch(Exception e){
            eligibilityResponseDtoResponse.setBody(eligibilityResponseDto);
            eligibilityResponseDtoResponse.setSuccess(false);
            eligibilityResponseDtoResponse.setMessage("Eligibility check failed");
        }
        return eligibilityResponseDtoResponse;
    }

    @CrossOrigin
    @PostMapping(path = "/saveVacancyDetails")
    public ResponseEntity<String> saveVacancyDetails(@RequestBody VacancyCategoryWiseDto vacancyCategoryWiseDto){
        if(applicationFormFacade.saveVacancyDetails(vacancyCategoryWiseDto)){
            return new ResponseEntity<>("lead saved", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Lead failed to save", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
