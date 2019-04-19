package sample.utils;

import java.util.Calendar;
import java.util.Date;

public final class DateUtils {

    private static final  Calendar cal = Calendar.getInstance() ;
    private DateUtils(){

    }

    public static Date getDate(int year , int month , int day){
        cal.set(year,month,day);
        return cal.getTime();
    }
}
