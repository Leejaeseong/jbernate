package com.jbernate.tt.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.jbernate.cm.bean.Sb;
import com.jbernate.cm.service.CmService;
import com.jbernate.cm.util.ArrUtil;
import com.jbernate.tt.domain.table.TtOneTable;
import com.jbernate.tt.service.P00005Service;

@Service
public class P00005ServiceImpl implements P00005Service{
	
	@Autowired CmService cmService;

	/**
	 * 테이블 로딩 Form 테스트
	 */
	public Model load( HttpSession session, HttpServletRequest request, Model model ) {
		TtOneTable ttOneTable = new TtOneTable();
		@SuppressWarnings("unchecked")
		List<TtOneTable> list = cmService.list( request, ttOneTable );
		if( ArrUtil.chkBlank( list ) ) {
			ttOneTable = list.get( 0 );
		}
		
		Sb sb = new Sb();
		sb.setTtOneTable( ttOneTable );
		
		model.addAttribute( "sb", sb );
		
		return model;
	}
}