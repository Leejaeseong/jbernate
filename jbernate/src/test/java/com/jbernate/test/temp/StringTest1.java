package com.jbernate.test.temp;

public class StringTest1 {

	public static void main(String[] args) {
		String a = "com.jbernate.controller.test.T00002Controller";
		System.out.println( a.substring( 0, a.lastIndexOf( ".",  a.lastIndexOf( "." ) - 1 ) ) );
		
		System.out.println( a.substring( a.lastIndexOf( ".", a.lastIndexOf( "." ) - 1 ) + 1, a.lastIndexOf( "." ) ) 
		+ "/" 
		+ a.substring( a.lastIndexOf( "." ) + 1, a.indexOf( "Controller" ) ) );
	}

}