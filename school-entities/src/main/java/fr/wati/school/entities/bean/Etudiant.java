/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Rachid Ouattara
 * 
 */
@SuppressWarnings("serial")
@Entity
public class Etudiant extends Personne {

	@OneToMany(mappedBy = "etudiant")
	private Set<Inscription> inscriptions;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "etudiant_parent", joinColumns = { @JoinColumn(name = "etudiantId") }, inverseJoinColumns = { @JoinColumn(name = "parentId") })
	private Set<Parent> parents;
	@ManyToOne
	private Classe classe;

	/**
	 * @return the inscriptions
	 */
	public Set<Inscription> getInscriptions() {
		return inscriptions;
	}

	/**
	 * @param inscriptions
	 *            the inscriptions to set
	 */
	public void setInscriptions(Set<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

}
