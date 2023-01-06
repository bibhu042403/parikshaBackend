package com.example.pariksha.service.impl;

import com.example.pariksha.dao.*;
import com.example.pariksha.model.*;
import com.example.pariksha.service.ApplicationFormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ApplicationFormServiceImpl implements ApplicationFormService {

    @Autowired
    ApplicationFormRepository applicationFormRepository;

    @Autowired
    ApplicationAgeLimitRepository applicationAgeLimitRepository;

    @Autowired
    ApplicationFeeDetailsRepository applicationFeeDetailsRepository;

    @Autowired
    ApplyDetailsRepository applyDetailsRepository;

    @Autowired
    ExamDetailsRepository examDetailsRepository;

    @Autowired
    QualificationDetailsRepository qualificationDetailsRepository;

    @Autowired
    VacancyPostWiseRepository vacancyPostWiseRepository;

    @Autowired
    VacancyCategoryWiseRepository vacancyCategoryWiseRepository;

    @Autowired
    ApplicationFormDateRepository applicationFormDateRepository;



    @Override
    public boolean saveLead(ApplicationForm applicationForm) {
        try{
            ApplicationForm saveApplicationForm = applicationFormRepository.save(applicationForm);
            if(saveApplicationForm!= null){
                return true;
            }
            else {
                return false;
            }
        }
        catch(Exception e){
            log.info("Exception while saving ");
        }
        return false;
    }

    @Override
    public List<UserDetails> getFormDetails(String examName) {

        return null;
    }

    @Override
    public Optional<ApplicationForm> fetchUsingExamId(String examName) {

        return applicationFormRepository.findApplicationFormByExamId(examName);
    }

    @Override
    public boolean saveApplicationFormDate(ApplicationFormDate applicationFormDate) {
        try{
            ApplicationFormDate saveApplicationFormDate = applicationFormDateRepository.save(applicationFormDate);
            return saveApplicationFormDate != null;
        }
        catch(Exception e){
            log.info("Exception while saving ");
        }
        return false;
    }

    @Override
    public boolean saveApplicationFeeDetails(ApplicationFeeDetails applicationFeeDetails) {
        try{
            ApplicationFeeDetails saveApplicationFeeDetails = applicationFeeDetailsRepository.save(applicationFeeDetails);
            return saveApplicationFeeDetails != null;
        }
        catch(Exception e){
            log.info("Exception while saving ");
        }
        return false;
    }

    @Override
    public boolean saveApplicationAgeLimit(ApplicationAgeLimit applicationAgeLimit) {
        try{
            ApplicationAgeLimit saveApplicationAgeLimit = applicationAgeLimitRepository.save(applicationAgeLimit);
            return saveApplicationAgeLimit != null;
        }
        catch(Exception e){
        log.info("Exception while saving ");
        }
        return false;
}

    @Override
    public boolean saveApplyDetails(ApplyDetails applyDetails) {
        try{
            ApplyDetails saveApplyDetails = applyDetailsRepository.save(applyDetails);
            return saveApplyDetails != null;
        }
        catch(Exception e){
        log.info("Exception while saving ");
        }
        return false;
    }

    @Override
    public boolean saveExamDetails(ExamDetails examDetails) {
        try{
            ExamDetails saveExamDetails = examDetailsRepository.save(examDetails);
            return saveExamDetails != null;
        }
        catch(Exception e){
        log.info("Exception while saving ");
        }
        return false;
    }

    @Override
    public boolean saveQualificationDetails(QualificationDetails qualificationDetails) {
        try{
            QualificationDetails saveQualificationDetails = qualificationDetailsRepository.save(qualificationDetails);
            return saveQualificationDetails != null;
        }
        catch(Exception e){
        log.info("Exception while saving ");
        }
        return false;
    }

    @Override
    public boolean saveVacancyCategoryWise(VacancyCategoryWise vacancyCategoryWise) {
        try{
            VacancyCategoryWise saveVacancyCategoryWise = vacancyCategoryWiseRepository.save(vacancyCategoryWise);
            return saveVacancyCategoryWise != null;
        }
        catch(Exception e){
        log.info("Exception while saving ");
        }
        return false;

    }

    @Override
    public boolean saveVacancyPostWise(VacancyPostWise vacancyPostWise){
        try{
            VacancyPostWise saveVacancyPostWise = vacancyPostWiseRepository.save(vacancyPostWise);
            return saveVacancyPostWise != null;
        }
        catch(Exception e){
            log.info("Exception while saving ");
        }
        return false;
        }

    @Override
    public boolean saveApplicationForm(ApplicationForm applicationForm) {
        try{
            ApplicationForm saveApplicationForm = applicationFormRepository.save(applicationForm);
            return saveApplicationForm != null;
        }
        catch(Exception e){
            log.info("Exception while saving ");
        }
        return false;
    }

    @Override
    public List<ApplicationForm> getLastFiveApplication(Date date) {
        return null;
    }

    @Override
    public Optional<List<ApplicationForm>> getAllLimited(int LIMIT) {
        Page<ApplicationForm> page = applicationFormRepository.findAll(PageRequest.of(0,LIMIT, Sort.by(Sort.Order.asc("lastDate"))));
        return Optional.of(page.getContent());
    }

    @Override
    public Optional<List<ApplicationForm>> getAllLatestData(int LIMIT) {
        Page<ApplicationForm> page = applicationFormRepository.findAll(PageRequest.of(0,LIMIT, Sort.by(Sort.Order.asc("dateCreated"))));
        return Optional.of(page.getContent());
    }
}
