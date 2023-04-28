package com.example.pariksha.utlis;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;


public class ParikshaUtils {
    public static int calculateAge(String dobString) {
        LocalDate dob = LocalDate.parse(dobString);
        LocalDate currentDate = LocalDate.now();
        return Period.between(dob, currentDate).getYears();
    }

    public static Date parseStringToDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(date);
    }
}
