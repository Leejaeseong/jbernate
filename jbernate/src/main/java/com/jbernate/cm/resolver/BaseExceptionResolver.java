package com.jbernate.cm.resolver;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.jbernate.cm.util.ConstUtil;
import com.jbernate.cm.util.LogUtil;
import com.jbernate.cm.util.StrUtil;

public class BaseExceptionResolver implements HandlerExceptionResolver {
	private String view = null;
	public void setView(String view) {
		this.view = view;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception) {
		
		StringWriter err	= new StringWriter();								// 오류를 문자화
		String errOneLineMsg 	= "";											// 오류 위치( 페이지 포함 전체 한줄 ) : DB에 들어가면 좋을 항목
		String errPgmIdMsg		= "";											// 오류 페이지
		String regStr 			= ConstUtil.ID_PACKAGE_PREFIX + "(.*?)\\n";	// 정규식
		
		exception.printStackTrace( new PrintWriter( err ) );
		String errCauseMsg 	= exception.toString();
		
		// 오류에서 위치 및 페이지 구분
		Pattern pattern			= Pattern.compile( regStr, Pattern.DOTALL );
		Matcher matcher			= pattern.matcher( err.toString() );
		if( matcher.find() )	errOneLineMsg = matcher.group();
		if( StrUtil.chkBlank( errOneLineMsg ) ) {					// 오류 위치 추출
			regStr = "P(.*?)\\(";
			pattern = Pattern.compile( regStr, Pattern.DOTALL );
			matcher = pattern.matcher( errOneLineMsg.toString() );
			if( matcher.find() ){										// 오류 프로그램ID 추출
				errPgmIdMsg = matcher.group();
				if( StrUtil.chkBlank( errPgmIdMsg ) && errPgmIdMsg.length() > ConstUtil.FORMAT_PAGE_LEN ) {
					errPgmIdMsg = errPgmIdMsg.substring( 0, ConstUtil.FORMAT_PAGE_LEN );
				}
			}
		}
		
		// TODO [jbernate] DB에 오류 삽입 로직 처리		
		//LogUtil.error( "cause : " + errCause );
		//LogUtil.error( "error full msg : " + err.toString() );
		exception.printStackTrace();	// 콘솔에 오류 출력
		
		request.setAttribute( "exceptionProgramId", errPgmIdMsg );
		return new ModelAndView(view);
	}
}