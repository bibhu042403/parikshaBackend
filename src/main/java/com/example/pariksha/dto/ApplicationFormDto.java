package com.example.pariksha.dto;

import com.example.pariksha.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@NoArgsConstructor
public class ApplicationFormDto {
    ExamDetails examDetails;
    ApplicationAgeLimit applicationAgeLimit;
    ApplicationFeeDetails applicationFeeDetails;
    ApplicationFormDate applicationFormDate;
    ApplyDetails applyDetails;
    QualificationDetails qualificationDetails;
    VacancyCategoryWise vacancyCategoryWise;
    VacancyPostWise vacancyPostWise;
}
