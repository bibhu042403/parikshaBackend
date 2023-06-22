package com.example.pariksha.service;

import com.example.pariksha.dto.ApplicationFormDTO;
import com.example.pariksha.dto.EligibilityCheckRequestDto;
import com.example.pariksha.dto.EligibilityResponseDto;

import java.util.List;

public interface ApplicationFormService {

    EligibilityResponseDto checkEligibility(EligibilityCheckRequestDto eligibilityCheckRequestDto, ApplicationFormDTO applicationFormDto);

    List<ApplicationFormDTO> getAllAppData();
}
