package com.test.basic.topscoreranking.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateFormatUtil {
	public static Date addMoreDay(int numDay) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		//creating the instance of LocalDate using the day, month, year info
        LocalDate localDate = LocalDate.of(2016, 8, 19);
        
        //local date + atStartOfDay() + default time zone + toInstant() = Date
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        return date;
	}
}
