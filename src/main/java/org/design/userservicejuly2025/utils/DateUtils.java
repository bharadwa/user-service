package org.design.userservicejuly2025.utils;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class DateUtils {


    public Date getTokenExpiration() {

        Date currentDate = new Date();
        System.out.println("Current date: " + currentDate);

        // Create Calendar instance and set the date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        // Add days (e.g., add 30 days)
        calendar.add(Calendar.DATE, 30);
        return calendar.getTime();
    }
}
