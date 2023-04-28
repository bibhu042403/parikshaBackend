package com.example.pariksha.service;

import com.example.pariksha.dto.ApplicationFormDto;
import com.example.pariksha.dto.EligibilityCheckRequestDto;
import com.example.pariksha.dto.EligibilityResponseDto;

public interface ApplicationFormService {

    EligibilityResponseDto checkEligibility(EligibilityCheckRequestDto eligibilityCheckRequestDto, ApplicationFormDto applicationFormDto);
}
