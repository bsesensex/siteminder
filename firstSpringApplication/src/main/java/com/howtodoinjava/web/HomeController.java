package com.howtodoinjava.web;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.howtodoinjava.model.CustomUser;




@Controller
public class HomeController {
	
	@RequestMapping(value="/secured/home", method = RequestMethod.GET)
	public String securedHome(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CustomUser user=null;
		if (principal instanceof CustomUser) {
		user = ((CustomUser)principal);
		String name = user.getUsername();
		model.addAttribute("username", name);
		}
		model.addAttribute("username", "Hiren");
	model.addAttribute("message", "Welcome to the secured page through site minder");
	return "home";
	 
	}
	
}