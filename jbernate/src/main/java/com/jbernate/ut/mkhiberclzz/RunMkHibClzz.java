package com.jbernate.ut.mkhiberclzz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jbernate.cm.service.CmService;
import com.jbernate.cm.util.ChkUtil;
import com.jbernate.cm.util.ConstUtil;
import com.jbernate.cm.util.StrUtil;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "classpath:spring/context/application-config.xml" } )
@TransactionConfiguration( transactionManager = "transactionManager" )
@Transactional
public class RunMkHibClzz {

	@Autowired 	ApplicationContext context;	
	@Autowired	CmService cmService;
	
	@Value( "${mkjava.db_name}" )		String tDbName;		// 데이터베이스명
	@Value( "${mkjava.target_path}" )	String tPath;		// 소스 만들 위치
	@Value( "${source.prefix}" )		String tPrefix;		// 소스 Prefix	
	@Value( "${mkjava.postfix}" )		String tPostfix;	// 소스 Postfix	
	@Value( "${mkjava.mkdb_arr}" )		String tMkdbArr;	// 대상 데이터베이스
	
	private List<Object> 	list = null;
	private File			file;
	private BufferedWriter	bw;
	private String			dbNm = "";
	private String			tabNm = "";
	private boolean			isFirstTable = false;
	
	@Test
	public void run() {
		if( tDbName.equals( ConstUtil.ID_DBTYPE_ORACLE ) ) {			// 오라클
			list = new Oracle( cmService, tPath, tPrefix, tMkdbArr ).getList();
		}else if( tDbName.equals( ConstUtil.ID_DBTYPE_SQLServer ) ) {	// SQL Server
			list = new SQLServer( cmService, tPath, tPrefix, tMkdbArr ).getList();
		}
		
		if( ChkUtil.chkBlank( list ) ) {
			for( int i = 0; i < list.size(); i++ ) {
				Object[] ent = (Object[])list.get( i );
				
				isFirstTable = !tabNm.equals( ent[ 1 ] );
				
				dbNm 	= ent[ 0 ].toString();
				tabNm 	= ent[ 1 ].toString();
				
				try{
					// 디렉토리가 없다면 생성
					file = new File( tPath + File.separator + tPrefix.replace( ".", File.separator ) + File.separator + dbNm.toLowerCase() + File.separator + tPostfix.replace( ".", File.separator ) );
					if ( !file.exists() ) file.mkdirs();
					
					// 테이블명이 다르면 파일 생성
					if( isFirstTable ) {
						// 처음이 아니라면 파일 마지막 부분 생성
						if( i > 0 ){
							bw = MkTail.mkCont( bw, ent, dbNm, tPrefix, tPostfix );
							bw.flush();
							bw.close();
						}
						
						// ex) com.jbernate.tt.domain.table.테이블명
						file = new File( tPath + File.separator + tPrefix.replace( ".", File.separator ) + File.separator + dbNm.toLowerCase() + File.separator + tPostfix.replace( ".", File.separator ) + File.separator + StrUtil.makeJavaFileNameRule( ent[ 1 ].toString().toLowerCase() ) + ".java" );
						try {	bw = new BufferedWriter( new FileWriter( file ) );	} catch (IOException e) {	e.printStackTrace();	}
						
						// 파일 첫 부분 생성
						bw = MkHead.mkCont( bw, ent, dbNm, tPrefix, tPostfix );
					}
					
					// ▣ 본문 생성 시작 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣---------
					
					// Single PK
					// PK이고, 관계 테이블/컬럼이 미존재
					// PK는 아니지만 SEQ인 경우(VIEW)도 여기에서 생성
					if( ( ent[ 11 ].toString().equals( "Y" ) || ent[ 3 ].toString().toUpperCase().equals( "SEQ" ) ) && !StrUtil.chkBlank( ent[ 13 ] ) ){	
						MkSinglePk.mkCont( bw, ent, dbNm, tPrefix, tPostfix );					
					}
					// OneToOne PK
					if( ent[ 11 ].toString().equals( "Y" ) && StrUtil.chkBlank( ent[ 13 ] ) ){	// PK이고, 관계 테이블/컬럼이 존재
						MkOneToOnePk.mkCont( bw, ent, dbNm, tPrefix, tPostfix, tDbName );					
					}
					// ManyToOne
					if( ent[ 11 ].toString().equals( "N" ) && StrUtil.chkBlank( ent[ 13 ] ) ){	// PK아니고, 관계 테이블/컬럼이 존재
						MkManyToOne.mkCont( bw, ent, dbNm, tPrefix, tPostfix, tDbName );					
					}
					// 숫자
					if( ent[ 11 ].toString().equals( "N" ) && !StrUtil.chkBlank( ent[ 13 ] ) && ( ent[ 5 ].toString().equals( "NUMBER") || ent[ 5 ].toString().equals( "NUMERIC") ) ){
						MkNumber.mkCont( bw, ent, dbNm, tPrefix, tPostfix, tDbName );					
					}
					// 문자
					if( ent[ 11 ].toString().equals( "N" ) && !StrUtil.chkBlank( ent[ 13 ] ) && ent[ 5 ].toString().indexOf( "VARCHAR" ) != -1 ){	// NVARCHAR, VARCHAR2포함
						MkString.mkCont( bw, ent, dbNm, tPrefix, tPostfix, tDbName );
					}
					// CLOB
					if( ent[ 11 ].toString().equals( "N" ) && !StrUtil.chkBlank( ent[ 13 ] ) && ent[ 5 ].toString().equals( "CLOB" ) ){
						MkCLob.mkCont( bw, ent, dbNm, tPrefix, tPostfix, tDbName );
					}
					// BLOB
					if( ent[ 11 ].toString().equals( "N" ) && !StrUtil.chkBlank( ent[ 13 ] ) && ent[ 5 ].toString().equals( "BLOB" ) ){
						MkBLob.mkCont( bw, ent, dbNm, tPrefix, tPostfix, tDbName );
					}
					// DATE
					if( ent[ 11 ].toString().equals( "N" ) && !StrUtil.chkBlank( ent[ 13 ] ) && ent[ 5 ].toString().equals( "DATE" ) ){
						MkDate.mkCont( bw, ent, dbNm, tPrefix, tPostfix, tDbName );
					}
					
					// ▣ 본문 생성 끝 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣---------
					
					// 배열의 끝이라면 파일 마지막 부분 생성
					if( i + 1 == list.size() ){
						bw = MkTail.mkCont( bw, ent, dbNm, tPrefix, tPostfix );
						bw.flush();
						bw.close();
					}
					
				}catch( IOException e ) {
					e.printStackTrace();
					if( bw != null ) try{ bw.close(); }catch( IOException ee ){ ee.printStackTrace(); }
				}
			}// end of for( int i = 0; i < list.size(); i++ )
		}// end of if( ChkUtil.chkBlank( list ) )
	}// end of public void run()
		
}