package com.ytbot.website.utils;

import java.time.LocalDateTime;

public class DateFormatterUtils {
    private DateFormatterUtils(){

    }
    public static String formatDate(LocalDateTime localDateTime) {
        String year = localDateTime.toString().substring(0, 4);
        String month = localDateTime.toString().substring(5, 7);
        String day = localDateTime.toString().substring(8, 10);
        String time = localDateTime.toString().substring(11, 19);
        String res = time + " " + day + "." + month + "." + year;
        return res;
    }

    public static LocalDateTime formatDate(String res) {
        int hour = Integer.parseInt(res.substring(0, 2));
        int min = Integer.parseInt(res.substring(3, 5));
        int sec = Integer.parseInt(res.substring(6, 8));
        int day = Integer.parseInt(res.substring(9, 11));
        int month = Integer.parseInt(res.substring(12, 14));
        int year = Integer.parseInt(res.substring(15));
        return LocalDateTime.of(year, month, day, hour, min, sec);
    }
}
