/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Entity
public class Etudiant extends Personne {

	@OneToMany
	private List<Inscription> inscriptions;
	@ManyToMany
	private List<Parent> parents;

	
	
	/**
	 * @return the inscriptions
	 */
	public List<Inscription> getInscriptions() {
		return inscriptions;
	}

	/**
	 * @param inscriptions the inscriptions to set
	 */
	public void setInscriptions(List<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}
	
	
}
