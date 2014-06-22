/**
 * 
 */
package fr.wati.scool.web.view;

import fr.wati.scool.web.addons.ViewDescription;

/**
 * @author Rachid Ouattara
 *
 */
@ViewDescription(name = BibliothequeView.NAME, requiredPermissions = "isAuthenticated()")
@SuppressWarnings("serial")
public class BibliothequeView extends AbstractView {

	/**
	 * 
	 */
	public static final String NAME = "bibliotheque";
	
	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.AbstractView#postConstruct()
	 */
	@Override
	protected void postConstruct() {
	}

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.AbstractView#getViewName()
	 */
	@Override
	public String getViewName() {
		return NAME;
	}

}
