package com.jbernate.cm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class SecuUtil {
	
	/**
	 * sha-256 해시값
	 */
	public static String getSha256( String str ) {
		String SHA = "";
		
		try{
			MessageDigest sh = MessageDigest.getInstance( "SHA-256" ); // MD5 가능
			sh.update( str.getBytes() );
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for( int i = 0; i < byteData.length; i++ ) {
				sb.append( Integer.toString( ( byteData[i]&0xff ) + 0x100, 16 ).substring( 1 ) );
			}
			SHA = sb.toString();
		}catch( NoSuchAlgorithmException e ) {
			e.printStackTrace();
			SHA = null;
		}
		
		return SHA;
	}
	
}