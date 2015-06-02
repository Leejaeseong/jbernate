package com.jbernate.tt.temp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jbernate.cm.util.DateUtil;


public class DateTest1 {

	public static void main(String[] args) {
		try {
			/*String from = "2013-04-08 10:10:10";
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date to = transFormat.parse( from );
			System.out.println( to );
			*/
			
			System.out.println( DateUtil.dtToStr( new Date(), "yyyy-MM" ) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}