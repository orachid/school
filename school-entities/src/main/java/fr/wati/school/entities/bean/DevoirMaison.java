/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.Future;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Entity
public class DevoirMaison extends ExerciseNote {

	@Future
	protected Date dateARendre;

	/**
	 * @return the dateARendre
	 */
	public Date getDateARendre() {
		return dateARendre;
	}

	/**
	 * @param dateARendre the dateARendre to set
	 */
	public void setDateARendre(Date dateARendre) {
		this.dateARendre = dateARendre;
	}
	
	
}
