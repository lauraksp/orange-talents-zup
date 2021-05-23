package br.com.zup.orangetalents.utils;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class Utils {

    public static String getDayWeek(){
        return new DateFormatSymbols().getWeekdays()[Calendar.getInstance().get(Calendar.DAY_OF_WEEK)];
    }
}
