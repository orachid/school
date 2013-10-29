/**
 * 
 */
package fr.wati.scool.web.view.admin.users;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

import fr.wati.scool.web.addons.ViewDescription;
import fr.wati.scool.web.view.admin.AbstractAdminView;

/**
 * @author Rachid Ouattara
 *
 */
@ViewDescription(name = UsersRightsManagementView.NAME, requiredPermissions = "isAuthenticated()")
@SuppressWarnings("serial")
public class UsersRightsManagementView extends AbstractAdminView {

	/**
	 * @param navigator
	 */
	public UsersRightsManagementView() {
		super();
	}


	public static final String NAME="usersRightsManagement";
	
	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.AbstractView#getViewName()
	 */
	@Override
	public String getViewName() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.admin.AbstractAdminView#getContent()
	 */
	@Override
	public Component getContent() {
		return new Label("dvdsfds");
	}

}
