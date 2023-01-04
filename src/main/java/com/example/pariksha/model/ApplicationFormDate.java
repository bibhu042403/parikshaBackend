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
@Table(name= "application_form_date")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
public class ApplicationFormDate {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "exam_id")
    private String examId;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "last_date")
    private String lastDate;

    @Column(name = "last_offline_challan_date")
    private String lastOfflineChallanDate;

    @Column(name = "last_online_pay_date")
    private String lastOnlinePayDate;
}
