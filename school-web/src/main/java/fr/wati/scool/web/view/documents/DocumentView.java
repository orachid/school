/**
 * 
 */
package fr.wati.scool.web.view.documents;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.FilesystemContainer;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

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
	private Tree myDocumentsTree;
	private Table folderContentTable;
	
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
		myDocumentsTree = new Tree();
		Document userDocumentRoot = documentManager.getUserDocument(SpringSecurityHelper.getUser().getUsername());
		DocumentHierarchicalContainer documentHierarchicalContainer = new DocumentHierarchicalContainer(userDocumentRoot);
		myDocumentsTree.setContainerDataSource(documentHierarchicalContainer);
		myDocumentsTree.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		myDocumentsTree.setItemCaptionPropertyId("name");
		myDocumentsTree.setImmediate(true);
		myDocumentsTree.addValueChangeListener(this);
		myDocumentsTree.setItemIconPropertyId(DocumentHierarchicalContainer.ICON_PROPERTY);
		documentContainer = new BeanItemContainer<>(Document.class);
		//select by default
		myDocumentsTree.setValue(userDocumentRoot);
		
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
		
		folderContentTable = new Table("", documentContainer);
		folderContentTable.setSelectable(true);
		folderContentTable.setVisibleColumns(new Object[]{"name", "directory","size","lastModificationDate"});
		folderContentTable.setImmediate(true);
		folderContentTable.addActionHandler(new Handler() {
			
			@Override
			public void handleAction(Action action, Object sender, Object target) {
				System.out.println(action+" "+sender+" "+target);
			}
			
			@Override
			public Action[] getActions(Object target, Object sender) {
				List<Action> actions=new ArrayList<>();
				actions.add(new Action("Delete"));
				return actions.toArray(new Action[actions.size()]);
			}
		});
		folderContentTable.addItemClickListener(new ItemClickListener() {
			
			@SuppressWarnings("rawtypes")
			@Override
			public void itemClick(ItemClickEvent event) {
				//Double Click
				if(event.isDoubleClick()){
					if(event.getItem()!=null && (event.getItem() instanceof BeanItem) && (((BeanItem)event.getItem()).getBean()!=null)){
						Document selectedDocument=(Document) ((BeanItem)event.getItem()).getBean();
						if(selectedDocument.isDirectory()){
							myDocumentsTree.setValue(selectedDocument);
						}
					}
				}
			}
		});
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
		if(documentContainer!=null){
			documentContainer.removeAllItems();
		}	
		if(event.getProperty() !=null &&event.getProperty().getValue()!=null && event.getProperty().getValue() instanceof Document){
			Document selectedDocumentInTree=(Document) event.getProperty().getValue();
			if(selectedDocumentInTree.isDirectory()){
				documentContainer.addAll(selectedDocumentInTree.getDocuments());
				return;
			}
		}
	}

}
