package com.siteminder.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import com.siteminder.model.CustomUser;
import com.siteminder.model.Role;

public class CustomUserDetailsService implements UserDetailsService
{
	public UserDetails loadUserByUsername(String username)
	        throws UsernameNotFoundException, DataAccessException
    {
		System.out.println("username recieved :: " + username);
		@SuppressWarnings("deprecation")
		
		
		CustomUser customUser = new CustomUser();
		customUser.setFirstName(username);
		customUser.setLastName("gc");
		customUser.setUsername("kb");
		customUser.setPassword("1234");
 
       
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if(username.equalsIgnoreCase("501")){
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
          
        }else  if(username.equalsIgnoreCase("200")){
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));

        }

        return new User(username, "password", true, true, true, true,
        		authorities);
    }
	
	private List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = null;
		
		if(CollectionUtils.isEmpty(roles)) {
			return new ArrayList<GrantedAuthority>();
		}
		
		authorities = new ArrayList<GrantedAuthority>(roles.size());
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
	/**
	 * Return instance of {@code ProfileAuthenticationToken} indicating successful authentication.
	 * 
	 * @param profile the {@code ProfileDTO} object
	 * @param roles the list of user roles
	 * @return the instance of {@code ProfileAuthenticationToken}
	 */
	private ProfileAuthenticationToken getAuthenticationSuccess(CustomUser profile, List<String> roles) {
		return new ProfileAuthenticationToken(profile, getGrantedAuthorities(roles));
	}
}
