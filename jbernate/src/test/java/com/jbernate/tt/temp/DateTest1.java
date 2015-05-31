package com.jbernate.tt.temp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateTest1 {

	public static void main(String[] args) {
		try {
			String from = "2013-04-08 10:10:10";
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date to = transFormat.parse( from );
			System.out.println( to );
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}