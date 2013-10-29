package fr.wati.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author xpoft
 */
public class SpringSecurityHelper {
	
	public static boolean hasAnyRole(String... roles) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		for (String role : roles) {
			if(user.getAuthorities().contains(new SimpleGrantedAuthority(role))){
				return true;
			}
		}
		return false;
	}

	public static boolean hasAllRoles(String... roles) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		for (String role : roles) {
			if(!user.getAuthorities().contains(new SimpleGrantedAuthority(role))){
				return false;
			}
		}
		return true;
	}
	
	public static User getUser(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
}
