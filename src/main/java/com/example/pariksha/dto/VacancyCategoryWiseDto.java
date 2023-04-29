package com.example.pariksha.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class VacancyCategoryWiseDto {
    private int id;
    private String examId;
    private int general;
    private int obc;
    private int eObc;
    private int obcFemale;
    private int ews;
    private int sc;
    private int st;
    private int total;
    String dateCreated;
    Date dateModified;
}
