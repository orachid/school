/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;


/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Entity
public class Cours extends Evenement {

	@OneToOne
	private Professeur professeur;
	@OneToOne
	private Matiere matiere;
	@OneToOne
	private Classe classe;
	@OneToOne
	private Periode periode;
	
	
	
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
	/**
	 * @return the periode
	 */
	public Periode getPeriode() {
		return periode;
	}
	/**
	 * @param periode the periode to set
	 */
	public void setPeriode(Periode periode) {
		this.periode = periode;
	}	
	
}
