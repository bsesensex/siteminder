	package com.siteminder.web;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siteminder.model.CustomUser;
import com.siteminder.model.User;
import com.siteminder.model.Users;

@Controller
@RequestMapping("/users")
public class DemoController 
{
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	CacheManager cacheManager;
	@RequestMapping(method = RequestMethod.GET, value="/{id}", headers="Accept=application/xml")
	public @ResponseBody User getUserById(@PathVariable String id) 
	{
		 Cache mapOFCache= cacheManager.getCache("mySimpleCache");
		 mapOFCache.put(1,"2");
		System.out.println("Pring the cache");
		mapOFCache.put(2,"3");
		//System.out.println(cacheManager.getCache("mySimpleCache").get(SecurityContextHolder.getContext().getAuthentication().hashCode()+""));
		System.out.println(cacheManager.getCache("mySimpleCache").getName());
		System.out.println(cacheManager.getCache("mySimpleCache").get(-1));
	
		System.out.println("over of print the cache");
		User user = new User();
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(		userDetails.getAuthorities().size());
		user.setFirstName("john");
		user.setLastName("adward");
		cacheManager.getCache("default").put(SecurityContextHolder.getContext().getAuthentication().hashCode(),user);
		System.out.println("200+ 4500");
	
		System.out.println("___"+cacheManager.getCache("default").get(SecurityContextHolder.getContext().getAuthentication().hashCode()).toString());;

		return user;
	}
	
	@RequestMapping(method = RequestMethod.GET,  headers="Accept=application/xml")
	public @ResponseBody Users getAllUsers() 
	{UserDetails userDetails =
	 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(		userDetails.getAuthorities().size());
		System.out.println(		userDetails.getAuthorities().size());
		System.out.println(		userDetails.getUsername() +" "+ userDetails);
		User user1 = new User();
		user1.setFirstName("john");
		user1.setLastName("adward");
	System.out.println(messageSource.getMessage("logoff.url", new Object[]{}, Locale.getDefault()));
		
		User user2 = new User();
		user2.setFirstName("tom");
		user2.setLastName("hanks");
		
		Users users = new Users();
		users.setUsers(new ArrayList<User>());
		users.getUsers().add(user1);
		users.getUsers().add(user2);
		
		return users;
	}
	

	
	

	@RequestMapping(value="/manager",method = RequestMethod.GET,  headers="Accept=application/xml")
	public @ResponseBody Users getForManager() 
	{UserDetails userDetails =
	 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(		userDetails.getAuthorities().size());
		System.out.println(		userDetails.getAuthorities().size());
		System.out.println(		userDetails.getUsername() +" "+ userDetails);
		User user1 = new User();
		user1.setFirstName("john");
		user1.setLastName("adward");
		
		User user2 = new User();
		user2.setFirstName("tom");
		user2.setLastName("hanks");
		
		Users users = new Users();
		users.setUsers(new ArrayList<User>());
		users.getUsers().add(user1);
		users.getUsers().add(user2);
		
		return users;
	}
	
	
	@RequestMapping(value="/member",method = RequestMethod.GET)
	public @ResponseBody Users getForMember() 
	{UserDetails userDetails =
	 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(		userDetails.getAuthorities().size());
		System.out.println(		userDetails.getAuthorities().size());
		System.out.println(		userDetails.getUsername() +" "+ userDetails);
		User user1 = new User();
		user1.setFirstName("I am member");
		user1.setLastName("Member");
		
		User user2 = new User();
		user2.setFirstName("I am member");
		user2.setLastName("Member");
		
		Users users = new Users();
		users.setUsers(new ArrayList<User>());
		users.getUsers().add(user1);
		users.getUsers().add(user2);
		
		return users;
	}
	@RequestMapping(value="/secured/home", method = RequestMethod.GET)
	public String securedHome(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CustomUser user=null;
		if (principal instanceof CustomUser) {
		user = ((CustomUser)principal);
		String name = user.getUsername();
		model.addAttribute("username", name);
		}
	 
	model.addAttribute("message", "Welcome to the secured page through site minder");
	return "home";
	 
	}
	
}
