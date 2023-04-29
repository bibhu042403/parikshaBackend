package com.example.pariksha.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EligibilityCheckRequestDto {
    String examId;
    String category;
    String gender;
    String ex_emp;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    String Dob;
}
