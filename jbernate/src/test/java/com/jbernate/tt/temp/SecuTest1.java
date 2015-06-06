package com.jbernate.tt.temp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.jbernate.cm.util.SecuUtil;
import com.jbernate.cm.util.StrUtil;


public class SecuTest1 {

	public static void main(String[] args) {
		System.out.println( SecuUtil.getSha256( "mundi!234" ) );
	}

}