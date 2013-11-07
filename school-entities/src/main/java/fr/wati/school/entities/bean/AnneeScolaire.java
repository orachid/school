/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.Set;

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
	@OneToMany(cascade=CascadeType.ALL, mappedBy="anneeScolaire")
	private Set<Periode> periodes;

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
	 * @return the periodes
	 */
	public Set<Periode> getPeriodes() {
		return periodes;
	}

	/**
	 * @param periodes the periodes to set
	 */
	public void setPeriodes(Set<Periode> periodes) {
		this.periodes = periodes;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
