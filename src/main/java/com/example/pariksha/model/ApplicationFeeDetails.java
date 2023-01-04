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
@Table(name= "application_fee_details")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
public class ApplicationFeeDetails {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "exam_id")
    private String examId;
    @Column(name = "general")
    private String general;
    @Column(name = "obc")
    private String obc;
    @Column(name = "ews")
    private String ews;
    @Column(name = "st_sc")
    private String stSc;
    @Column(name = "correction_fee_1")
    private String correctionFee1;
    @Column(name = "correction_fee_2")
    private String correctionFee2;
    @Column(name = "mode_of_payment")
    private String modeOfPayment;
}
