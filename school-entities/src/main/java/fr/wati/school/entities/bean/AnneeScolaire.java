/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Entity
public class AnneeScolaire extends Entite {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String label;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Inscription> inscriptions;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Periode> periodes;

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the inscriptions
	 */
	public List<Inscription> getInscriptions() {
		return inscriptions;
	}

	/**
	 * @param inscriptions the inscriptions to set
	 */
	public void setInscriptions(List<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

	/**
	 * @return the periodes
	 */
	public List<Periode> getPeriodes() {
		return periodes;
	}

	/**
	 * @param periodes the periodes to set
	 */
	public void setPeriodes(List<Periode> periodes) {
		this.periodes = periodes;
	}
	
	
}
