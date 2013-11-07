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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Entity
public class Classe extends Entite {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String code;
	private String nom;
	private Integer effectifTotal;
	@ManyToOne
	private Etablissement etablissement;
	@OneToMany(mappedBy="classe")
	private Set<Matiere> matieres=new HashSet<>();
	@OneToMany(mappedBy="classe")
	private Set<Etudiant> etudiants=new HashSet<>();
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
	public Set<Matiere> getMatieres() {
		return matieres;
	}
	/**
	 * @param matieres the matieres to set
	 */
	public void setMatieres(Set<Matiere> matieres) {
		this.matieres = matieres;
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
	 * @return the etablissement
	 */
	public Etablissement getEtablissement() {
		return etablissement;
	}
	/**
	 * @param etablissement the etablissement to set
	 */
	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}
	
}
