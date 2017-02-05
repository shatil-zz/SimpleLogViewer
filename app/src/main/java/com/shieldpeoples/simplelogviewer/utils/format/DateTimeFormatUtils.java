package com.shieldpeoples.simplelogviewer.utils.format;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by shatil on 2/4/17.
 */
public class DateTimeFormatUtils {

    public static long getTimeStampFromDateTime(String dateTimeString) throws ParseException {
        DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        m_ISO8601Local.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = m_ISO8601Local.parse(dateTimeString);
        return date.getTime();
    }

    public static String getHumanReadableDateTime(long timeStamp) {
        // TODO Auto-generated method stub
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timeStamp);
            return getHumanReadableTime(calendar) + ", " + getHumanReadableDate(calendar);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    private static String getHumanReadableDate(Calendar calendar) {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US).substring(0, 3);
        int year = calendar.get(Calendar.YEAR);
        return day + getOrdinalForDate(day) + " " + monthName + " " + year;
    }

    private static String getHumanReadableTime(Calendar calendar) {
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        minute += (minute.length() == 1) ? "0" : "";
        int hour = calendar.get(Calendar.HOUR);
        if (hour == 0 && calendar.get(Calendar.AM_PM) == 1) hour = 12;
        return String.valueOf(hour) + ":" + minute +
                " " + (calendar.get(Calendar.AM_PM) == 1 ? "PM" : "AM");
    }


    public static String getOrdinalForDate(int dateOfMonth) {
        switch (dateOfMonth % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
        }
        return "th";
    }
}
