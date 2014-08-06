package com.jbernate.cm.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jbernate.cm.bean.Sb;

public class SbValidator implements Validator{

	public SbValidator(){}
	
	/**
	 * 검증기가 검증할 수 있는 오브젝트 타입인지를 확인해 주는 함수
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return ( Sb.class.isAssignableFrom( clazz ) );
	}

	/**
	 * supports() 함수를 통과한 경우에만 호출 
	 * 
	 * Constraint 종류
	 * 		pk는 validation 제외( insert시 pk값 없음 )
	 * 		@AssertFalse : false 값만 통과 가능
	 * 		@AssertTrue : true 값만 통과 가능
	 * 		@DecimalMax(value=) : 지정된 값 이하의 실수만 통과 가능
	 * 		@DecimalMin(value=) : 지정된 값 이상의 실수만 통과 가능
	 * 		@Digits(integer=,fraction=) : 대상 수가 지정된 정수와 소수 자리수보다 적을 경우 통과 가능
	 * 		@Future : 대상 날짜가 현재보다 미래일 경우만 통과 가능
	 * 		@Past : 대상 날짜가 현재보다 과거일 경우만 통과 가능
	 * 		@Max(value) : 지정된 값보다 아래일 경우만 통과 가능
	 * 		@Min(value) : 지정된 값보다 이상일 경우만 통과 가능
	 * 		@NotNull : null 값이 아닐 경우만 통과 가능
	 * 		@Null : null일 겨우만 통과 가능
	 * 		@Pattern(regex=, flag=) : 해당 정규식을 만족할 경우만 통과 가능, ex. 이메일 = @Pattern(regexp="^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*$")
	 * 		@Size(min=, max=) : 문자열 또는 배열이 지정된 값 사이일 경우 통과 가능
	 * 		@Valid : 대상 객체의 확인 조건을 만족할 경우 통과 가능
	 */
	@Override
	public void validate(Object target, Errors errors) {
		Sb sb = (Sb)target;
		/*
		TtOneTable ttOneTable = sb.getTtOneTable();
		if( ttOneTable != null ) {
			 if( ttOneTable.gettVarchar() == null || ttOneTable.gettVarchar().length() < 10 ) errors.rejectValue( "ttOneTable.tVarchar", "test.test1" );			
		}
		*/
		
	}
}