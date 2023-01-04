package com.example.pariksha.controller;

import com.example.pariksha.dto.ApplicationFormDto;
import com.example.pariksha.facade.ApplicationFormFacade;
import com.example.pariksha.model.ApplicationForm;
import com.example.pariksha.model.UserDetails;
import com.example.pariksha.service.ApplicationFormService;
import com.example.pariksha.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "/leads")
public class LeadController {

   @Autowired
   LeadService leadService;
   @Autowired
   ApplicationFormService applicationFormService;

   @Autowired
   ApplicationFormFacade applicationFormFacade;

   @CrossOrigin
   @PostMapping(path = "/saveLead")
   public ResponseEntity<String> saveLead(@RequestBody UserDetails userDetails){
       if(leadService.saveLead(userDetails)){
           return new ResponseEntity<>("lead saved", HttpStatus.OK);
       }
       else
           return new ResponseEntity<>("Lead failed to save", HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @CrossOrigin
   @GetMapping(path = "/getLead")
   public ResponseEntity<List<UserDetails>> getLead(){
       return new ResponseEntity<>(leadService.getLead(),HttpStatus.OK);
   }

   @CrossOrigin
    @PostMapping(path = "/saveFormDetails")
   public ResponseEntity<String> saveFormDetail(@RequestBody ApplicationForm applicationForm){
       if(applicationFormService.saveLead(applicationForm)){
           return new ResponseEntity<>("lead saved", HttpStatus.OK);
       }
       else
           return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @CrossOrigin
    @GetMapping(path = "/getFormDetails")
    public ApplicationFormDto getLeads(@RequestParam String examName){
       ApplicationFormDto applicationFormDto = applicationFormFacade.getFormDetails(examName);
       return applicationFormDto;
   }

}
