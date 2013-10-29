/**
 * 
 */
package fr.wati.scool.web.view.admin.parameters;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

import fr.wati.scool.web.addons.ViewDescription;
import fr.wati.scool.web.view.admin.AbstractAdminView;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@ViewDescription(name = LogView.NAME, requiredPermissions = "isAuthenticated()")
public class LogView extends AbstractAdminView {

	public static final String NAME="logs";

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.admin.AbstractAdminView#getContent()
	 */
	@Override
	public Component getContent() {
		return new Label("Logs");
	}

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.AbstractView#getViewName()
	 */
	@Override
	public String getViewName() {
		return NAME;
	}

}
