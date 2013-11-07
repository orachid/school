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

/**
 * @author Rachid Ouattara
 * 
 */
@SuppressWarnings("serial")
@Entity
public class Parent extends Personne {

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "etudiant_parent", joinColumns = { @JoinColumn(name = "parentId") }, inverseJoinColumns = { @JoinColumn(name = "etudiantId") })
	private Set<Etudiant> etudiants;

	/**
	 * @return the etudiants
	 */
	public Set<Etudiant> getEtudiants() {
		return etudiants;
	}

	/**
	 * @param etudiants
	 *            the etudiants to set
	 */
	public void setEtudiants(Set<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

}
