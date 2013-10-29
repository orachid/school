/**
 * 
 */
package fr.wati.school.entities.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Entity
public class Inscription extends Entite {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OneToOne
	private Classe classe;
	@OneToOne
	private AnneeScolaire anneeScolaire;
	@OneToOne
	private Etudiant etudiant;
	/**
	 * @return the classe
	 */
	public Classe getClasse() {
		return classe;
	}
	/**
	 * @param classe the classe to set
	 */
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	/**
	 * @return the anneeScolaire
	 */
	public AnneeScolaire getAnneeScolaire() {
		return anneeScolaire;
	}
	/**
	 * @param anneeScolaire the anneeScolaire to set
	 */
	public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}
	/**
	 * @return the etudiant
	 */
	public Etudiant getEtudiant() {
		return etudiant;
	}
	/**
	 * @param etudiant the etudiant to set
	 */
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	
	
}
