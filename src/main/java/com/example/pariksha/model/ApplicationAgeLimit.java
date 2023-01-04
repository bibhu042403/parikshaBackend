package com.example.pariksha.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "application_age_limit")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
public class ApplicationAgeLimit {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "exam_id")
    private String examId;

    @Column(name = "sc_st")
    private String stSc;

    @Column(name = "obc")
    private String obc;

    @Column(name = "pwd_unreserved")
    private String pwdUnreserved;

    @Column(name = "pwd_obc")
    private String pwdObc;

    @Column(name = "ex_servicemen")
    private String exServiceMen;

}
