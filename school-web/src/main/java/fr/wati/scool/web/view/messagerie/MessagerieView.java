/**
 * 
 */
package fr.wati.scool.web.view.messagerie;

import javax.annotation.PostConstruct;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

import fr.wati.scool.web.addons.ViewDescription;
import fr.wati.scool.web.view.AbstractView;

/**
 * @author Rachid Ouattara
 * 
 */
@ViewDescription(name = MessagerieView.NAME, requiredPermissions = "isAuthenticated()")
@SuppressWarnings("serial")
public class MessagerieView extends AbstractView {

	public static final String NAME = "messagerie";

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.wati.scool.web.view.AbstractView#postConstruct()
	 */
	@Override
	@PostConstruct
	public void postConstruct() {

		TabSheet tabsheet = new TabSheet();

		// Mail
		MailPanel mailPanel = new MailPanel();
		tabsheet.addTab(mailPanel, "Mail",new ThemeResource("img/mail-message-new.png"));
		
		// Make the tabsheet shrink to fit the contents.
		tabsheet.setHeight("500px");
		VerticalLayout verticalLayout = new VerticalLayout();

		verticalLayout.addComponent(tabsheet);
		setSizeFull();
		setCompositionRoot(verticalLayout);
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

}
