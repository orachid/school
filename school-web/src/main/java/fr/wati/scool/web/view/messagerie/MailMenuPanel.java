package fr.wati.scool.web.view.messagerie;

import com.vaadin.data.Item;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

public class MailMenuPanel extends VerticalLayout{
	
	private Tree tree;
	
	public MailMenuPanel(MailPanel mailPanel){
		tree = new Tree("Boite de rÃ©ception");
		tree.setContainerDataSource(constructItems());
		tree.setItemCaptionPropertyId("name");
		tree.setItemIconPropertyId("icon");
		addComponent(tree);
		setSizeFull();
	}
	
	public HierarchicalContainer constructItems() {
		Item item = null;
        int itemId = 0; // Increasing numbering for itemId:s

        // Create new container
        HierarchicalContainer hwContainer = new HierarchicalContainer();
        // Create containerproperty for name
        hwContainer.addContainerProperty("name", String.class, null);
        // Create containerproperty for icon
        //hwContainer.addContainerProperty("icon", ClassResource.class, new ClassResource(MailMenuPanel.class,"icons/emblem-mail.png",this.openlvpApplication));
        hwContainer.addContainerProperty("icon", ThemeResource.class, new ThemeResource("icons/emblem-mail.png"));
        
        //brouillons
        item = hwContainer.addItem(itemId);
        item.getItemProperty("name").setValue("Brouillons");
        item.getItemProperty("icon").setValue(new ThemeResource("icons/mail_new.png"));
        itemId++;
        //elements envoyes
        item = hwContainer.addItem(itemId);
        item.getItemProperty("name").setValue("ElÃ©ments envoyÃ©s");
        item.getItemProperty("icon").setValue(new ThemeResource("icons/mail_send.png"));
        itemId++;
        //elements supprimes
        item = hwContainer.addItem(itemId);
        item.getItemProperty("name").setValue("ElÃ©ments supprimÃ©s");
        item.getItemProperty("icon").setValue(new ThemeResource("icons/mail_delete.png"));
        itemId++;
        //tags
        item = hwContainer.addItem(itemId);
        item.getItemProperty("name").setValue("Tags");
        item.getItemProperty("icon").setValue(new ThemeResource("icons/fileopen.png"));
        return hwContainer;
	}
	

}