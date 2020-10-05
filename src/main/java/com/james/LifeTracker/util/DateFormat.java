package com.james.LifeTracker.util;

import org.ocpsoft.prettytime.PrettyTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormat {

    private static final PrettyTime prettyTime = new PrettyTime();

    public static String getTimeAgo(LocalDateTime localDateTime) {
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return prettyTime.format(Date.from(zdt.toInstant()));
    }

    public static String getDateString(LocalDateTime localDateTime){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return localDateTime.format(dateFormatter).toString();
    }
}
