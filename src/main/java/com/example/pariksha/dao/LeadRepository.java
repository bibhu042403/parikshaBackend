package com.example.pariksha.dao;

import com.example.pariksha.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<UserDetails, Integer> {

}
