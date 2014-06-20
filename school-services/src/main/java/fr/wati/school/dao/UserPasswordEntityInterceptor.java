package fr.wati.school.dao;

import java.io.Serializable;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.wati.school.entities.bean.Users;

@SuppressWarnings("serial")
public class UserPasswordEntityInterceptor extends EmptyInterceptor {

	private PasswordEncoder passwordEncoder;
	
	private static Log log = LogFactory
			.getLog(UserPasswordEntityInterceptor.class);

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		if (entity != null && entity instanceof Users) {
			log.debug("Intercept user onLoad() ==> remove password");
			return true;
		}
		return false;
	}

	@PrePersist
	public void create(Users users){
		log.debug("Pre persit....");
	}
	@PreUpdate
	public void update(Users users){
		log.debug("Pre update....");
	}
	
	@PostLoad
	public void load(Users users){
		log.debug("Post load....");
	}
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		if (entity != null && entity instanceof Users) {
			log.debug("Intercept user onSave() ==> encode password");
			return true;
		}
		return false;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		PasswordEncoder  passwordEncoder=new BCryptPasswordEncoder(11);
		System.out.println(passwordEncoder.encode("admin"));
	}
	
}