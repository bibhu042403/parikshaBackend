package com.example.pariksha.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EligibilityCheckRequestDto {
    String examId;
    String category;
    String gender;
    String ex_emp;
    String Dob;
}
