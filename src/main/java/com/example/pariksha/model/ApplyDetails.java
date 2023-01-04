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
@Table(name= "apply_details")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
public class ApplyDetails {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "exam_id")
    private String examId;

    @Column(name = "apply_online_link")
    private String applyOnlineLink;

    @Column(name = "download_notification")
    private String downloadNotification;

    @Column(name = "official_website")
    private String officialWebsite;
}
