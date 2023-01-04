package com.example.pariksha.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "form_details")
@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
public class ApplicationForm {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "exam_id")
    private String examId;

    // age limit
    @Column(name = "general_age")
    private String generalAge;

    @Column(name = "sc_st_age")
    private String stScAge;

    @Column(name = "obc_age")
    private String obcAge;

    @Column(name = "pwd_unreserved_age")
    private String pwdUnreservedAge;

    @Column(name = "pwd_obc_age")
    private String pwdObcAge;

    @Column(name = "ex_servicemen_age")
    private String exServiceMenAge;

    //fee details
    @Column(name = "general_fee")
    private String generalFee;

    @Column(name = "obc_fee")
    private String obcFee;

    @Column(name = "ews_fee")
    private String ewsFee;

    @Column(name = "st_sc_fee")
    private String stScFee;

    @Column(name = "pwd_obc_fee")
    private String pwdObcFee;

    @Column(name = "correction_fee_1_fee")
    private String correctionFee1Fee;

    @Column(name = "correction_fee_2_fee")
    private String correctionFee2Fee;

    @Column(name = "mode_of_payment_fee")
    private String modeOfPaymentFee;

    // form dates
    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "last_date")
    private Date lastDate;

    @Column(name = "last_offline_challan_date")
    private Date lastOfflineChallanDate;

    @Column(name = "last_online_pay_date")
    private Date lastOnlinePayDate;

    //apply details

    @Column(name = "apply_online_link")
    private String applyOnlineLink;

    @Column(name = "download_notification")
    private String downloadNotification;

    @Column(name = "official_website")
    private String officialWebsite;

    //qualification

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "date_created")
    String dateCreated;

    @Column(name = "date_modified")
    Date dateModified;

}
