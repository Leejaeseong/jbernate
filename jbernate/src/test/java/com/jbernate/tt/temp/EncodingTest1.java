package com.jbernate.tt.temp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.jbernate.cm.util.StrUtil;


public class EncodingTest1 {

	public static void main(String[] args) {
		try{
			MessageDigest sh = MessageDigest.getInstance( "SHA-256" ); // MD5 가능
			sh.update( "1".getBytes() );
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for( int i = 0; i < byteData.length; i++ ) {
				sb.append( Integer.toString( ( byteData[i]&0xff ) + 0x100, 16 ).substring( 1 ) );
			}
			System.out.println( sb.toString() );
		}catch( NoSuchAlgorithmException e ) {
			e.printStackTrace();
		}
	}

}