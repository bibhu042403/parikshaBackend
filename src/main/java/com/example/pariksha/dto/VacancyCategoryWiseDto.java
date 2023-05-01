package com.example.pariksha.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class VacancyCategoryWiseDto {
    private int id;
    private String examId;
    private String general;
    private String obc;
    private String eObc;
    private String obcFemale;
    private String ews;
    private String sc;
    private String st;
    private String total;
    String dateCreated;
    Date dateModified;
}
