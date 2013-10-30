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
public class ConseilleDeClasse extends Reunion {

	private String compteRendue;

	/**
	 * @return the compteRendue
	 */
	public String getCompteRendue() {
		return compteRendue;
	}

	/**
	 * @param compteRendue the compteRendue to set
	 */
	public void setCompteRendue(String compteRendue) {
		this.compteRendue = compteRendue;
	}
	
}
