package com.example.pariksha.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EligibilityResponseDto {
    String LastDate;
    String ApplicationFee;
    String vacancy;
    Boolean eligibility;
}
