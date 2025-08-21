package kz.railways.rbrestapp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {
	
	public static String getCurrentYear(){        
        final Calendar c = Calendar.getInstance();        
        return String.valueOf(c.get(Calendar.YEAR));
    }
    
	public static String getPreviousYear(){        
        final Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1);
        return String.valueOf(c.get(Calendar.YEAR));
    }
	
	public static String getPrevious2Year(){        
        final Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -2);
        return String.valueOf(c.get(Calendar.YEAR));
    }
	
	public static String getBeginDate(String year) {
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        cal.set(Calendar.DAY_OF_YEAR, 1);
        Date firstDay = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(firstDay);
	}
	
	public static String getPreviousBeginDateFromStr(String year) {
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(year)-1);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        Date firstDay = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(firstDay);
	}
	
	public static String getPreviousBeginYearFromStr(String year) {
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(year)-1);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        Date firstDay = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(firstDay);
	}
	
	public static String getCurrentBeginYearFromStr(String year) {
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        cal.set(Calendar.DAY_OF_YEAR, 1);
        Date firstDay = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(firstDay);
	}
	
	
}