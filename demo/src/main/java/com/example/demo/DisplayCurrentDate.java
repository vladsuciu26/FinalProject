package com.example.demo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DisplayCurrentDate {


    private static LocalDate currentDate = LocalDate.of(2021, 12, 15);

    private static Instant instant = currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
    private static Long timeInMillis = instant.toEpochMilli();

    public static LocalDate getCurrentDate() {
        return currentDate;
    }

    public static void setCurrentDate(LocalDate currentDate) {
        DisplayCurrentDate.currentDate = currentDate;
    }

    public static Long getTimeInMillis() {
        return timeInMillis;
    }

    public static void setTimeInMillis(Long timeInMillis) {
        DisplayCurrentDate.timeInMillis = timeInMillis;
    }
}
