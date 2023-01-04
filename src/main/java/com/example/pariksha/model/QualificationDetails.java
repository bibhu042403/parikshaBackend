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
@Table(name= "qualification_details")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
public class QualificationDetails {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "exam_id")
    private String examId;

    @Column(name = "qualification")
    private String qualification;
}
