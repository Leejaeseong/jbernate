package com.jbernate.test.temp;


public class StringTest1 {

	public static void main(String[] args) {
		String a = "com.jbernate.test.controller.T00002Controller";
		
		String[] aArr = a.split( "\\." );
		
		a =  aArr[ 2 ] + "/" + aArr[ 4 ].substring( 0, aArr[ 4 ].indexOf( "Controller" ) );
		System.out.println( a );
	}

}