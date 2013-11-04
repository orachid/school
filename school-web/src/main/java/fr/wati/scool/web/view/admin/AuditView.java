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
@SuppressWarnings("serial")
@ViewDescription(name = AuditView.NAME, requiredPermissions = "isAuthenticated()")
public class AuditView extends AbstractAdminView {

	public static final String NAME = "auditEditionView";
	
	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.admin.AbstractAdminView#getContent()
	 */
	@Override
	public Component getContent() {
		return new Label("Audit");
	}

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.AbstractView#getViewName()
	 */
	@Override
	public String getViewName() {
		return NAME;
	}

}
