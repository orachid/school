/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Email;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Entity
public class Contact extends Entite {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Email
	private String email;

	private String numeroTelephone;
	@OneToMany
	private List<Adresse> adresses;
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the numeroTelephones
	 */
	public String getNumeroTelephones() {
		return numeroTelephone;
	}
	/**
	 * @param numeroTelephones the numeroTelephones to set
	 */
	public void setNumeroTelephones(String numeroTelephone) {
		this.numeroTelephone = numeroTelephone;
	}
	/**
	 * @return the adresses
	 */
	public List<Adresse> getAdresses() {
		return adresses;
	}
	/**
	 * @param adresses the adresses to set
	 */
	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}
	
	
}
