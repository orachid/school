/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.HashSet;
import java.util.Set;

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
	
	private String nom;
	private String code;
	@OneToMany
	private Set<AnneeScolaire> anneeScolaires=new HashSet<>();

	/**
	 * @return the anneeScolaires
	 */
	public Set<AnneeScolaire> getAnneeScolaires() {
		return anneeScolaires;
	}

	/**
	 * @param anneeScolaires the anneeScolaires to set
	 */
	public void setAnneeScolaires(Set<AnneeScolaire> anneeScolaires) {
		this.anneeScolaires = anneeScolaires;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
