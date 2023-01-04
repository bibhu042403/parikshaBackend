package com.example.pariksha.service;

import com.example.pariksha.model.UserDetails;

import java.util.List;

public interface LeadService {
    boolean saveLead(UserDetails userDetails);

    List<UserDetails> getLead();

}
