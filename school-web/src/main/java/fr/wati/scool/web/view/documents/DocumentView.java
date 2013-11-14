/**
 * 
 */
package fr.wati.scool.web.view.documents;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.FilesystemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;

import fr.wati.school.entities.bean.Document;
import fr.wati.school.services.documents.DocumentManager;
import fr.wati.scool.web.addons.ViewDescription;
import fr.wati.scool.web.view.AbstractView;
import fr.wati.util.IconProvider;
import fr.wati.util.SpringSecurityHelper;


/**
 * @author Rachid Ouattara
 *
 */
@ViewDescription(name = DocumentView.NAME, requiredPermissions = "isAuthenticated()")
@SuppressWarnings("serial")
public class DocumentView extends AbstractView implements ValueChangeListener{

	public static final String NAME = "documents";
	@Autowired
	private DocumentManager documentManager;
	private BeanItemContainer<Document> documentContainer;
	
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
		myDocumentsTree.setImmediate(true);
		myDocumentsTree.addValueChangeListener(this);
		myDocumentsTree.setItemIconPropertyId(DocumentHierarchicalContainer.ICON_PROPERTY);
		
		browserVerticalLayout.addComponent(myDocumentsTree);
		browserVerticalLayout.addComponent(new Label("Available documents"));
		Tree availableDocumentsTree=new Tree();
		availableDocumentsTree.setContainerDataSource(new FilesystemContainer(new File("D:\\")));
		browserVerticalLayout.addComponent(availableDocumentsTree);
		//My documents
		
		//Folder content
		VerticalLayout contentVerticalLayout=new VerticalLayout();
		
		//toolbar
		HorizontalLayout toolBarHorizontalLayout=new HorizontalLayout();
		Button addFolderButton=new Button("Add");
		addFolderButton.setIcon(IconProvider.getIcone24X24(""));
		toolBarHorizontalLayout.addComponent(addFolderButton);
		contentVerticalLayout.addComponent(toolBarHorizontalLayout);
		documentContainer = new BeanItemContainer<>(Document.class);
		Table folderContentTable=new Table("", documentContainer);
		folderContentTable.setPageLength(documentContainer.size());
		folderContentTable.setVisibleColumns(new Object[]{"name", "directory","size","lastModificationDate"});
		folderContentTable.setImmediate(true);
		contentVerticalLayout.addComponent(folderContentTable);
		Panel sidePanel=new Panel(browserVerticalLayout);
		horizontalLayout.addComponent(sidePanel);
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

	/* (non-Javadoc)
	 * @see com.vaadin.data.Property.ValueChangeListener#valueChange(com.vaadin.data.Property.ValueChangeEvent)
	 */
	@Override
	public void valueChange(ValueChangeEvent event) {
		documentContainer.removeAllItems();
		if(event.getProperty() !=null &&event.getProperty().getValue()!=null && event.getProperty().getValue() instanceof Document){
			Document selectedDocumentInTree=(Document) event.getProperty().getValue();
			if(selectedDocumentInTree.isDirectory()){
				documentContainer.addAll(selectedDocumentInTree.getDocuments());
				return;
			}
		}
		System.out.println(event.getProperty().getValue());
	}

}
