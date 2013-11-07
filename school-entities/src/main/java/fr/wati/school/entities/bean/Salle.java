/**
 * 
 */
package fr.wati.school.entities.bean;

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
@Entity
@SuppressWarnings("serial")
public class Salle extends Entite {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private  Long id;
	private String nom;
	private String code;
	private Integer capacity;
	@OneToMany(mappedBy="salle")
	private Set<Evenement> evenements;
	
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
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public Set<Evenement> getEvenements() {
		return evenements;
	}
	public void setEvenements(Set<Evenement> evenements) {
		this.evenements = evenements;
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
	
	
}
