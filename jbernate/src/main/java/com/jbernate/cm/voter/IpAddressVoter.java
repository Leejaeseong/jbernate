package com.jbernate.cm.voter;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;


public class IpAddressVoter implements AccessDecisionVoter {
	
	public static final String IP_PREFIX = "IP_";
	public static final String IP_LOCAL_HOST = "IP_LOCAL_HOST";
	
	//@Autowired TcUserIpService tcUserIpService;
	//@Autowired LoginUserService loginUserService;
	
	@Override
	public boolean supports(ConfigAttribute attribute) {
		return attribute.getAttribute() != null && attribute.getAttribute().startsWith( IP_PREFIX );
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object object,
			Collection<ConfigAttribute> configList) {

		int result = ACCESS_ABSTAIN;
		
		if( !( authentication.getDetails() instanceof WebAuthenticationDetails ) ) {
			return ACCESS_DENIED;
		}
		
		WebAuthenticationDetails details = ( WebAuthenticationDetails ) authentication.getDetails();

		String userIp = details.getRemoteAddress();
		if( userIp.equals( "127.0.0.1" ) || userIp.equals( "0:0:0:0:0:0:0:1" ) || userIp.equals( "localhost" ) ) {
			return ACCESS_GRANTED;
		}
		
		//String userId = loginUserService.getUsername();
					
		//List<TcUserIp> list = tcUserIpService.getByUserIdUserIp(userId, userIp);
		
		//if( list != null && list.size() > 0 ) 	return ACCESS_GRANTED;
		//else									return ACCESS_DENIED;
		
		if( true ) 	return ACCESS_GRANTED;	// ACL allow all
		else 		return ACCESS_GRANTED;	// ACL allow all
	}

}
