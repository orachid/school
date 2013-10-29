/**
 * 
 */
package fr.wati.school.entities.bean;

import javax.persistence.Entity;


/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Entity
public class DevoirSurveille extends ExerciseNote {

	
	private PersonneAdministrative personneAdministrative;

	/**
	 * @return the personneAdministrative
	 */
	public PersonneAdministrative getPersonneAdministrative() {
		return personneAdministrative;
	}

	/**
	 * @param personneAdministrative the personneAdministrative to set
	 */
	public void setPersonneAdministrative(
			PersonneAdministrative personneAdministrative) {
		this.personneAdministrative = personneAdministrative;
	}
	
	
}
