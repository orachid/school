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
import javax.persistence.ManyToOne;
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
	private Set<Inscription> inscriptions;
	@OneToMany(cascade=CascadeType.ALL)
	private Set<Periode> periodes;
	@ManyToOne
	private Etablissement etablissement;

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
	public Set<Inscription> getInscriptions() {
		return inscriptions;
	}

	/**
	 * @param inscriptions the inscriptions to set
	 */
	public void setInscriptions(Set<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
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

	/**
	 * @return the etablissement
	 */
	public Etablissement getEtablissement() {
		return etablissement;
	}

	/**
	 * @param etablissement the etablissement to set
	 */
	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}
	
	
}
