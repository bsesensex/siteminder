package com.siteminder.security;

import java.util.Collection;


import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.siteminder.model.CustomUser;

/**
 * Authentication token class.
 *
 */
public class ProfileAuthenticationToken extends AbstractAuthenticationToken implements UserDetails {

	private static final long serialVersionUID = -2994079265907502633L;

	private CustomUser profile;
	
	private String redirectLink = "/";

	/**
	 * A constructor.
	 * 
	 * @param profile the {@code ProfileDTO}
	 * @param tenant the {@code BusinessUnitDTO}
	 * @param authorities the permissions list
	 */
	public ProfileAuthenticationToken(CustomUser profile,
			Collection<? extends GrantedAuthority> authorities) {
		
		super(authorities);
		this.profile = profile;
		setAuthenticated(true);
	}

	/**
	 * @return the profile
	 */
	public CustomUser getProfile() {
		return profile;
	}
	/**
	 * @param profile the profile to set
	 */
	public void setProfile(CustomUser profile) {
		this.profile = profile;
	}
	/**
	 * @return the redirectLink
	 */
	public String getRedirectLink() {
		return redirectLink;
	}
	/**
	 * @param redirectLink the redirectLink to set
	 */
	public void setRedirectLink(String redirectLink) {
		this.redirectLink = redirectLink;
	}

	@Override
	public Object getPrincipal() {
		//Return same value as present in SM_USER request header
		return profile.getUsername();
	}

	@Override
	public Object getCredentials() {
		return profile.getPassword();
	}

	@Override
	public String getPassword() {
		return profile.getUsername();
	}

	@Override
	public String getUsername() {
		return profile.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public String toString() {
		return "" ;
	}
}
