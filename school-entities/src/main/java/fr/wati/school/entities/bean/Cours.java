/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Entity
public class Cours extends Evenement {

	@ManyToOne
	private Professeur professeur;
	@ManyToOne
	private Matiere matiere;
	@ManyToOne
	private Classe classe;
	
	public Cours() {
		super();
	}
	public Cours(String nom, Date dateDebut, Date dateFin, String commentaire) {
		super(nom, dateDebut, dateFin, commentaire);
	}
	/**
	 * @return the professeur
	 */
	public Professeur getProfesseur() {
		return professeur;
	}
	/**
	 * @param professeur the professeur to set
	 */
	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}
	/**
	 * @return the matiere
	 */
	public Matiere getMatiere() {
		return matiere;
	}
	/**
	 * @param matiere the matiere to set
	 */
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
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
	
}
