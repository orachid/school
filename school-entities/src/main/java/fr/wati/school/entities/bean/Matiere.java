/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Entity
public class Matiere extends Entite {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nom;
	private String code;
	@ManyToMany
	private List<Classe> classes;
	private Long coefficient;
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
	 * @return the classes
	 */
	public List<Classe> getClasse() {
		return classes;
	}
	/**
	 * @param classe the classes to set
	 */
	public void setClasse(List<Classe> classes) {
		this.classes = classes;
	}
	/**
	 * @return the coefficient
	 */
	public Long getCoefficient() {
		return coefficient;
	}
	/**
	 * @param coefficient the coefficient to set
	 */
	public void setCoefficient(Long coefficient) {
		this.coefficient = coefficient;
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
