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
@Table(name= "vacancy_post_wise")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
public class VacancyPostWise {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "exam_id")
    private String examId;
    @Column(name = "army")
    private int army;
    @Column(name = "navy")
    private int navy;
    @Column(name = "airforce")
    private int airForce;
    @Column(name = "cadet")
    private int cadet;
    @Column(name = "total")
    private int total;
    @Column(name = "date_created")
    String dateCreated;

    @Column(name = "date_modified")
    Date dateModified;
}
