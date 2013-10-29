/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Rachid Ouattara
 *
 */
@Entity
@SuppressWarnings("serial")
public class Role extends Entite {

	public static final String ROLE_ADMIN="ROLE_ADMIN";
	public static final String ROLE_STUDENT="ROLE_STUDENT";
	public static final String ROLE_PROF="ROLE_PROF";
	public static final String ROLE_SECRETAIRE="ROLE_SECRETAIRE";
	public static final String ROLE_DIRECTOR="ROLE_DIRECTOR";
	public static final String ROLE_PARENT="ROLE_PARENT";
	
	public static List<String> availabeRoles=new ArrayList<String>();
	
	static{
		availabeRoles.add(ROLE_DIRECTOR);
		availabeRoles.add(ROLE_STUDENT);
		availabeRoles.add(ROLE_PROF);
		availabeRoles.add(ROLE_SECRETAIRE);
		availabeRoles.add(ROLE_ADMIN);
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private  Long id;
	private String role;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
