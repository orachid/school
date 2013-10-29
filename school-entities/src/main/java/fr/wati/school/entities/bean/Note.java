/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Range;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Entity
public class Note extends Entite {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Inscription inscription;
	private Matiere matiere;
	@Range(min=0L,max=1L)
	private Long coefficient=1L;
	private Long note;
	private Date dateNotation;
	@OneToOne
	private ExerciseNote exerciseNote;
	private Periode periode;
	private String appreciation;
	
	
	
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
	 * @return the inscription
	 */
	public Inscription getInscription() {
		return inscription;
	}
	/**
	 * @param inscription the inscription to set
	 */
	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
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
	 * @return the note
	 */
	public Long getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(Long note) {
		this.note = note;
	}
	/**
	 * @return the dateNotation
	 */
	public Date getDateNotation() {
		return dateNotation;
	}
	/**
	 * @param dateNotation the dateNotation to set
	 */
	public void setDateNotation(Date dateNotation) {
		this.dateNotation = dateNotation;
	}
	/**
	 * @return the appreciation
	 */
	public String getAppreciation() {
		return appreciation;
	}
	/**
	 * @param appreciation the appreciation to set
	 */
	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}
	/**
	 * @return the exerciseNote
	 */
	public ExerciseNote getExerciseNote() {
		return exerciseNote;
	}
	/**
	 * @param exerciseNote the exerciseNote to set
	 */
	public void setExerciseNote(ExerciseNote exerciseNote) {
		this.exerciseNote = exerciseNote;
	}
	
	
}
