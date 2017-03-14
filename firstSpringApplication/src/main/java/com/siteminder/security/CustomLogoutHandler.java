package com.siteminder.security;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.util.StringUtils;




public class CustomLogoutHandler extends SimpleUrlLogoutSuccessHandler {

	  protected Log logger = LogFactory.getLog(this.getClass());

	// Constructor setting the default target URL
	public CustomLogoutHandler(String targetURL) {	
			this.setDefaultTargetUrl(targetURL);
		
	}

	/**
	 * This method clears cookies and invalidate session(if present) with the request.
	 * @param request
	 * @param response
	 * @param authentication
	 * @return	
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("Clearing cookie on logout success. "+ " LOGOUT URL ==> "+ getDefaultTargetUrl() );
			}
			System.out.println("Clearing cookie on logout success. "+ " LOGOUT URL ==> "+ getDefaultTargetUrl() );
			Cookie cookie = new Cookie("mod_auth_openidc_session", null);
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			
			//Invalidating session(if any)
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			super.onLogoutSuccess(request, response, authentication);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Exception Occured in Custom Logout Handler :: " + e.getMessage());
			}
		}
	}
}
