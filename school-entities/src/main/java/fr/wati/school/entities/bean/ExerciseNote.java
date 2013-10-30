/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Entity
public class ExerciseNote extends Exercise {

	@OneToMany
	protected List<Note> notes;
	@OneToOne
	private Professeur professeur;

	/**
	 * @return the notes
	 */
	public List<Note> getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(List<Note> notes) {
		this.notes = notes;
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
	
	
	
}
