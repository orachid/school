/**
 * 
 */
package fr.wati.school.entities.bean;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import org.hibernate.validator.constraints.Email;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Embeddable
public class Contact implements Serializable {

	@Email
	private String email;

	private String numeroTelephone;
	@Embedded
	private Adresse adresse;
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
	
}
