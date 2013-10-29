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
import fr.wati.scool.web.addons.ViewDescription;
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
		VerticalLayout layout = new VerticalLayout();
		
		// We need an entity provider to create a container        
		CachingMutableLocalEntityProvider<Users> entityProvider =
		    new CachingMutableLocalEntityProvider<Users>(Users.class,
		                                                  entityManager);

		// And there we have it
		JPAContainer<Users> usersContainer =
		        new JPAContainer<Users> (Users.class);
		usersContainer.setEntityProvider(entityProvider);
		
//		JPAContainer<Users> usersContainer = new JPAContainer<>(Users.class);
//		EntityProvider<Users> usersEntityProvider = new CachingLocalEntityProvider<>(
//				Users.class, entityManager);
//		usersContainer.setEntityProvider(usersEntityProvider);

		Table usersTable = new Table("Users");
		usersTable.setContainerDataSource(usersContainer);
		layout.addComponent(usersTable);
		return layout;
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
