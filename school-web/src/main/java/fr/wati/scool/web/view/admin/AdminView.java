/**
 * 
 */
package fr.wati.scool.web.view.admin;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

import fr.wati.scool.web.addons.ViewDescription;

/**
 * @author Rachid Ouattara
 * 
 */
@ViewDescription(name = AdminView.NAME, requiredPermissions = "isAuthenticated() and hasRole('ROLE_ADMIN')")
@SuppressWarnings("serial")
public class AdminView extends AbstractAdminView  {

	public static final String NAME = "admin";



	/**
	 * 
	 */
	public AdminView() {
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

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.admin.AbstractAdminView#getContent()
	 */
	@Override
	public Component getContent() {
		return new Label("dsfdf");
	}

}
