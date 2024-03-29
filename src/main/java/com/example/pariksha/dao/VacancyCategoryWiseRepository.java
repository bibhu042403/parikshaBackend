package com.example.pariksha.dao;

import com.example.pariksha.model.ApplicationForm;
import com.example.pariksha.model.VacancyCategoryWise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyCategoryWiseRepository extends JpaRepository<VacancyCategoryWise,Integer> {

    VacancyCategoryWise findByExamId(String examId);
}
