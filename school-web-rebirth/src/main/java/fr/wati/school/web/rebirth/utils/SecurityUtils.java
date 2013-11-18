package fr.wati.school.web.rebirth.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import fr.wati.school.entities.bean.Users;
import fr.wati.school.services.dao.UsersRepository;

/**
 * @author Rachid-home
 */
@Component
public class SecurityUtils implements ApplicationContextAware{
	
	public static transient ApplicationContext applicationContext;
	
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
	
	public static Users getApplicationConnectedUser(){
		return applicationContext.getBean(UsersRepository.class).findByUsername(getUser().getUsername());
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SecurityUtils.applicationContext=applicationContext;
	}
}
