/**
 * 
 */
package fr.wati.school.entities.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	@ManyToOne
	private Classe classe;
	private Double coefficient;
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
	
	
	public Classe getClasse() {
		return classe;
	}
	public void setClasses(Classe classe) {
		this.classe = classe;
	}
	/**
	 * @return the coefficient
	 */
	public Double getCoefficient() {
		return coefficient;
	}
	/**
	 * @param coefficient the coefficient to set
	 */
	public void setCoefficient(Double coefficient) {
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
