package project.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static String getCurrentDateMinusOneHour() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -1);
        return formatter.format(cal.getTime());
    }

    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        Calendar cal = Calendar.getInstance();
        return formatter.format(cal.getTime());
    }
}
