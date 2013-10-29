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

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Entity
public class Etablissement extends Entite {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OneToMany
	private List<AnneeScolaire> anneeScolaires;

	/**
	 * @return the anneeScolaires
	 */
	public List<AnneeScolaire> getAnneeScolaires() {
		return anneeScolaires;
	}

	/**
	 * @param anneeScolaires the anneeScolaires to set
	 */
	public void setAnneeScolaires(List<AnneeScolaire> anneeScolaires) {
		this.anneeScolaires = anneeScolaires;
	}
	
	
}
