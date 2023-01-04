package com.example.pariksha.service.impl;

import com.example.pariksha.dao.LeadRepository;
import com.example.pariksha.model.UserDetails;
import com.example.pariksha.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadServiceImpl implements LeadService {

    @Autowired
    LeadRepository leadRepository;
    @Override
    public boolean saveLead(UserDetails userDetails) {
        try{
            UserDetails saveUserDetails = leadRepository.save(userDetails);
            if(saveUserDetails != null){
                return true;
            }
            else {
                return false;
            }
        }
        catch(Exception e){

        }
        return false;
    }

    @Override
    public List<UserDetails> getLead() {
        return leadRepository.findAll();
    }

//    @Override
//    public List<Lead> getLead() {
//        return null;
//    }
}
