package com.example.pariksha.service;

import com.example.pariksha.dto.ApplicationFormDTO;
import com.example.pariksha.dto.VacancyCategoryWiseDto;
import com.example.pariksha.model.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ApplicationFormDataService {
    boolean saveLead(ApplicationForm applicationForm);

    List<UserDetails> getFormDetails(String examName);

    Optional<ApplicationForm> fetchUsingExamId(String examName);

    boolean saveApplicationFormDate(ApplicationFormDate applicationFormDate);

    boolean saveApplicationFeeDetails(ApplicationFeeDetails applicationFeeDetails);

    boolean saveApplicationAgeLimit(ApplicationAgeLimit applicationAgeLimit);

    boolean saveApplyDetails(ApplyDetails applyDetails);

    boolean saveQualificationDetails(QualificationDetails qualificationDetails);

    boolean saveVacancyCategoryWise(VacancyCategoryWise vacancyCategoryWise);

    boolean saveVacancyPostWise(VacancyPostWise vacancyPostWise);

    boolean saveApplicationForm(ApplicationForm applicationForm);


    List<ApplicationForm> getLastFiveApplication(Date date);

    Optional<List<ApplicationForm>> getAllLimited(int LIMIT);

    List<ApplicationForm> getAllFormData();

    Optional<List<ApplicationForm>> getAllLatestData(int LIMIT);

    void deleteExpiredForm(Date date);

    ApplicationFormDTO getApplicationFormDtoForExamId(String examId);

    VacancyCategoryWiseDto getVacancyDetailsForExamId(String examId);
}
