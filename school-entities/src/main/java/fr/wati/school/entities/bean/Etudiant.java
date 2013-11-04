/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.Set;

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
	private Set<Inscription> inscriptions;
	@ManyToMany
	private Set<Parent> parents;

	
	
	/**
	 * @return the inscriptions
	 */
	public Set<Inscription> getInscriptions() {
		return inscriptions;
	}

	/**
	 * @param inscriptions the inscriptions to set
	 */
	public void setInscriptions(Set<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}
	
	
}
