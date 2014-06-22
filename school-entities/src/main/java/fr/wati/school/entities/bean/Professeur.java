/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Entity
public class Professeur extends Personne{

	@OneToMany(mappedBy="professeur")
	private Set<Cours> cours=new HashSet<>();

	/**
	 * @return the cours
	 */
	public Set<Cours> getCours() {
		return cours;
	}

	/**
	 * @param cours the cours to set
	 */
	public void setCours(Set<Cours> cours) {
		this.cours = cours;
	}
	
	
	
}
