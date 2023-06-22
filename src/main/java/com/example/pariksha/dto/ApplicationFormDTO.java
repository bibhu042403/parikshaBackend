package com.example.pariksha.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ApplicationFormDTO {
    private int id;
    private String examId;
    private String examName;

    // age limit
    private String femaleAge;
    private String generalAge;
    private String stScAge;
    private String obcAge;
    private String pwdUnreservedAge;
    private String pwdObcAge;
    private String exServiceMenAge;

    //fee details
    private String femaleFee;
    private String generalFee;
    private String obcFee;
    private String ewsFee;
    private String stScFee;
    private String pwdObcFee;
    private String correctionFee1Fee;
    private String correctionFee2Fee;
    private String modeOfPaymentFee;

    // form dates
    private Date startDate;
    private Date lastDate;
    private Date lastOfflineChallanDate;
    private Date lastOnlinePayDate;

    //apply details
    private String applyOnlineLink;
    private String downloadNotification;
    private String officialWebsite;

    //qualification
    private String qualification;
    String dateCreated;
    Date dateModified;


}
