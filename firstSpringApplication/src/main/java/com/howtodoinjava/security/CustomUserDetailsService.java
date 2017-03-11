package com.howtodoinjava.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.howtodoinjava.model.CustomUser;
import com.howtodoinjava.model.Role;

public class CustomUserDetailsService implements UserDetailsService
{
	public UserDetails loadUserByUsername(String username)
	        throws UsernameNotFoundException, DataAccessException
    {
		System.out.println("username recieved :: " + username);
		@SuppressWarnings("deprecation")
		UserDetails user = new User(username, "password", true, true, true, true,
				new GrantedAuthority[]{ new GrantedAuthorityImpl("ROLE_USER") });
		
		CustomUser customUser = new CustomUser();
		customUser.setFirstName("kb");
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
}
