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
public class Classe extends Entite {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	private String code;
	private String nom;
	private Integer effectifTotal;
	@ManyToMany
	private List<Matiere> matieres;
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
	/**
	 * @return the effectifTotal
	 */
	public Integer getEffectifTotal() {
		return effectifTotal;
	}
	/**
	 * @param effectifTotal the effectifTotal to set
	 */
	public void setEffectifTotal(Integer effectifTotal) {
		this.effectifTotal = effectifTotal;
	}
	/**
	 * @return the matieres
	 */
	public List<Matiere> getMatieres() {
		return matieres;
	}
	/**
	 * @param matieres the matieres to set
	 */
	public void setMatieres(List<Matiere> matieres) {
		this.matieres = matieres;
	}
	
}
