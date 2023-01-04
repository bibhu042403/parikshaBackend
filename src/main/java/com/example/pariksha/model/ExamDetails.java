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
@Table(name= "exam_details")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
public class ExamDetails {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "exam_name")
    private String examName;

    @Column(name = "exam_id")
    private String examId;

    @Column(name = "exam_agency_name")
    private String examAgencyName;

    @Column(name = "last_date")
    private String lastDate;

    @Column(name = "apply_online_link")
    private String applyOnlineLink;

    @Column(name = "date_created")
    String dateCreated;

    @Column(name = "date_modified")
    Date dateModified;
}
