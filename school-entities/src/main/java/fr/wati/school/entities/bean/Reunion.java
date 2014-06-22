/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Entity
public class Reunion extends Evenement {

	@OneToMany
	private Set<Personne> personnesPresentes;

	/**
	 * @return the personnesPresentes
	 */
	public Set<Personne> getPersonnesPresentes() {
		return personnesPresentes;
	}

	/**
	 * @param personnesPresentes the personnesPresentes to set
	 */
	public void setPersonnesPresentes(Set<Personne> personnesPresentes) {
		this.personnesPresentes = personnesPresentes;
	}
	
	
}
