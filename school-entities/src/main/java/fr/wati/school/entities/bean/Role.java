/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * @author Rachid Ouattara
 * 
 */
@Entity
@SuppressWarnings("serial")
public class Role extends Entite {

	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_STUDENT = "ROLE_STUDENT";
	public static final String ROLE_PROF = "ROLE_PROF";
	public static final String ROLE_SECRETAIRE = "ROLE_SECRETAIRE";
	public static final String ROLE_DIRECTOR = "ROLE_DIRECTOR";
	public static final String ROLE_PARENT = "ROLE_PARENT";

	public static List<String> availabeRoles = new ArrayList<String>();

	static {
		availabeRoles.add(ROLE_DIRECTOR);
		availabeRoles.add(ROLE_STUDENT);
		availabeRoles.add(ROLE_PROF);
		availabeRoles.add(ROLE_SECRETAIRE);
		availabeRoles.add(ROLE_ADMIN);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String role;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "roleId") }, inverseJoinColumns = { @JoinColumn(name = "userId") })
	private Set<Users> users = new HashSet<>();

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the users
	 */
	public Set<Users> getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
