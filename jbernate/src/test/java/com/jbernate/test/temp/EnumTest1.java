package com.jbernate.test.temp;

import oracle.sql.DATE;

import org.jboss.logging.Logger;


public class EnumTest1 {

	public static enum Clause{ EQ, GE, LE, LIKEANY, LIKEPRE, LIKEPOST, IN, ISNULL, ISNOTNULL };
	
	public static void main(String[] args) {
		System.out.println( EnumTest1.Clause.ISNOTNULL.toString() );
		
	}

}