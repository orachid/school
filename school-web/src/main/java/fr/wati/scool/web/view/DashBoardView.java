/**
 * 
 */
package fr.wati.scool.web.view;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import fr.wati.scool.web.addons.ViewDescription;

/**
 * @author Rachid Ouattara
 *
 */
@ViewDescription(name = DashBoardView.NAME, requiredPermissions = "isAuthenticated()")
@SuppressWarnings("serial")
public class DashBoardView extends AbstractView {

	
	/**
	 * 
	 */
	public static final String NAME = "";

	/**
	 * @param navigator 
	 * 
	 */
	public DashBoardView() {
		VerticalLayout layout=new VerticalLayout();
		layout.addComponent(new Label("Here is the dashboard"));
		setCompositionRoot(layout);
	}

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.AbstractView#getViewName()
	 */
	@Override
	public String getViewName() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.AbstractView#postConstruct()
	 */
	@Override
	protected void postConstruct() {
	}

}
