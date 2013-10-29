/**
 * 
 */
package fr.wati.scool.web.view.admin.users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import fr.wati.school.entities.bean.Users;
import fr.wati.scool.web.addons.SpringSecurityViewProvider;
import fr.wati.scool.web.addons.ViewDescription;
import fr.wati.scool.web.components.BasicCRUDView;
import fr.wati.scool.web.components.DefaultCRUDPanel;
import fr.wati.scool.web.view.admin.AbstractAdminView;

/**
 * @author Rachid Ouattara
 * 
 */
@ViewDescription(name = UsersEditionView.NAME, requiredPermissions = "isAuthenticated()")
@SuppressWarnings("serial")
public class UsersEditionView extends AbstractAdminView {

	public static final String NAME = "usersEditionView";
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * @param navigator
	 */
	public UsersEditionView() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.wati.scool.web.view.AbstractView#getViewName()
	 */
	@Override
	public String getViewName() {
		return NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.wati.scool.web.view.admin.AbstractAdminView#getContent()
	 */
	@Override
	public Component getContent() {
		DefaultCRUDPanel<Users> usersCrudPanel=(DefaultCRUDPanel<Users>) SpringSecurityViewProvider.applicationContext.getBean("defaultCRUDPanel", Users.class,"Utilisateurs");
		usersCrudPanel.setVisibleFormProperties("username","password","enabled");
		usersCrudPanel.setVisibleTableProperties("id","username","password","enabled");
		return usersCrudPanel;
	}

	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
