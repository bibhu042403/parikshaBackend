package com.example.pariksha.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name= "vacancy_category_wise")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
public class VacancyCategoryWise {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "exam_id")
    private String examId;

    @Column(name = "general")
    private String general;

    @Column(name = "obc")
    private String obc;

    @Column(name = "e_obc")
    private String eObc;

    @Column(name = "obc_female")
    private String obcFemale;

    @Column(name = "ews")
    private String ews;

    @Column(name = "sc")
    private String sc;

    @Column(name = "st")
    private String st;

    @Column(name = "total")
    private String total;
    @Column(name = "date_created")
    String dateCreated;

    @Column(name = "date_modified")
    Date dateModified;
}
