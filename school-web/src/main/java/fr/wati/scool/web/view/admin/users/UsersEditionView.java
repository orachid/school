/**
 * 
 */
package fr.wati.scool.web.view.admin.users;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.ui.Component;

import fr.wati.school.entities.bean.Users;
import fr.wati.scool.web.addons.ViewDescription;
import fr.wati.scool.web.components.CRUDPanelFactory;
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
	
	@Autowired
	private CRUDPanelFactory crudPanelFactory;

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
		DefaultCRUDPanel<Users> usersCrudPanel=crudPanelFactory.getCRUDPanel(Users.class,"Utilisateurs","Edition des utilisateurs");
		return usersCrudPanel;
	}

}
