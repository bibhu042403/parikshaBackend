package com.example.pariksha.controller;

import com.example.pariksha.dto.ApplicationFormDto;
import com.example.pariksha.facade.ApplicationFormFacade;
import com.example.pariksha.model.ApplicationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path= "/form")
public class FormController {
    @Autowired
    ApplicationFormFacade applicationFormFacade;

    @CrossOrigin
    @PostMapping(path = "/saveForms")
    public ResponseEntity<String> saveLead(@RequestBody ApplicationFormDto applicationFormDto){
        if(applicationFormFacade.saveApplicationForm(applicationFormDto)){
            return new ResponseEntity<>("lead saved", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Lead failed to save", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @CrossOrigin
    @PostMapping(path = "/saveFormsDetails")
    public ResponseEntity<String> saveForms(@RequestBody ApplicationForm applicationForm){
        if(applicationFormFacade.saveApplication(applicationForm)){
            return new ResponseEntity<>("lead saved", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Lead failed to save", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @CrossOrigin
    @GetMapping(path = "/getFiveLastData")
    public List<ApplicationForm> getFiveLastData(){
        List<ApplicationForm> list = applicationFormFacade.getFiveLastData();
        return list;
    }

    @CrossOrigin
    @GetMapping(path = "/getFiveLatestData")
    public List<ApplicationForm> getFiveLatestData(){
        List<ApplicationForm> list = applicationFormFacade.getFiveLastData();
        return list;
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


}
