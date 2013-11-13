/**
 * 
 */
package fr.wati.scool.web.view.documents;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.util.FilesystemContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;

import fr.wati.school.services.documents.DocumentManager;
import fr.wati.scool.web.addons.ViewDescription;
import fr.wati.scool.web.view.AbstractView;
import fr.wati.util.SpringSecurityHelper;


/**
 * @author Rachid Ouattara
 *
 */
@ViewDescription(name = DocumentView.NAME, requiredPermissions = "isAuthenticated()")
@SuppressWarnings("serial")
public class DocumentView extends AbstractView {

	public static final String NAME = "documents";
	@Autowired
	private DocumentManager documentManager;
	
	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.AbstractView#postConstruct()
	 */
	@PostConstruct
	@Override
	protected void postConstruct() {
		Panel contentPanel=new Panel();
		HorizontalLayout horizontalLayout=new HorizontalLayout();
		VerticalLayout browserVerticalLayout=new VerticalLayout();
		browserVerticalLayout.addStyleName("sidebar-menu");
		browserVerticalLayout.addComponent(new Label("My documents"));
		Tree myDocumentsTree=new Tree();
		myDocumentsTree.setContainerDataSource(new DocumentHierarchicalContainer(documentManager.getUserDocument(SpringSecurityHelper.getUser().getUsername())));
		myDocumentsTree.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		myDocumentsTree.setItemCaptionPropertyId("name");
		browserVerticalLayout.addComponent(myDocumentsTree);
		browserVerticalLayout.addComponent(new Label("Available documents"));
		Tree availableDocumentsTree=new Tree();
		availableDocumentsTree.setContainerDataSource(new FilesystemContainer(new File("D:\\")));
		browserVerticalLayout.addComponent(availableDocumentsTree);
		//My documents
		
		//Folder content
		VerticalLayout contentVerticalLayout=new VerticalLayout();
		
		horizontalLayout.addComponent(browserVerticalLayout);
		contentPanel.setContent(contentVerticalLayout);
		horizontalLayout.addComponent(contentPanel);
		
		setCompositionRoot(horizontalLayout);
	}

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.AbstractView#getViewName()
	 */
	@Override
	public String getViewName() {
		return NAME;
	}

}
