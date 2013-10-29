/**
 * 
 */
package fr.wati.scool.web.components;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;

import fr.wati.school.entities.bean.Entite;

/**
 * @author Rachid Ouattara
 *
 */
public class AbstractCRUDPanel<ENTITY extends Entite> extends CustomComponent {

	private Table table;
	private JPAContainer<ENTITY> jpaContainer;
	
	@PersistenceContext
	private EntityManager entityManager;
}
