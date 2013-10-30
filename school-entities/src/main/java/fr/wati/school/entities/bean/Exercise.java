/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Exercise extends Entite {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	protected Matiere matiere;
	protected Date dateProgrammation;
	protected Date dateExecution;
	protected String commentaire;
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
	 * @return the dateProgrammation
	 */
	public Date getDateProgrammation() {
		return dateProgrammation;
	}
	/**
	 * @param dateProgrammation the dateProgrammation to set
	 */
	public void setDateProgrammation(Date dateProgrammation) {
		this.dateProgrammation = dateProgrammation;
	}
	
	/**
	 * @return the commentaire
	 */
	public String getCommentaire() {
		return commentaire;
	}
	/**
	 * @param commentaire the commentaire to set
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	/**
	 * @return the dateExecution
	 */
	public Date getDateExecution() {
		return dateExecution;
	}
	/**
	 * @param dateExecution the dateExecution to set
	 */
	public void setDateExecution(Date dateExecution) {
		this.dateExecution = dateExecution;
	}
	
	
}
