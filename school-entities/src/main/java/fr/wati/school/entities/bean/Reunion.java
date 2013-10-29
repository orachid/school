/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.List;

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
	private List<Personne> personnesPresentes;

	/**
	 * @return the personnesPresentes
	 */
	public List<Personne> getPersonnesPresentes() {
		return personnesPresentes;
	}

	/**
	 * @param personnesPresentes the personnesPresentes to set
	 */
	public void setPersonnesPresentes(List<Personne> personnesPresentes) {
		this.personnesPresentes = personnesPresentes;
	}
	
	
}
