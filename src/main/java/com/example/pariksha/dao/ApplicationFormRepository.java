package com.example.pariksha.dao;

import com.example.pariksha.model.ApplicationForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm,Integer> {
    @Override
    Optional<ApplicationForm> findById(Integer integer);

    Optional<ApplicationForm> findApplicationFormByExamId(String examName);

    Page<ApplicationForm> findAll(Pageable pageable);

    void deleteByLastDateLessThan(Date date);

    ApplicationForm findByExamId(String examId);
}
