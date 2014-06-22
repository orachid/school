package fr.wati.scool.web.view.messagerie;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jensjansson.pagedtable.PagedTable;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MailMainPanel extends VerticalLayout{
	
	DateFormat dateFormat = new SimpleDateFormat("HH'h'mm dd-MM-yyyy");

	public MailMainPanel(MailPanel mailPanel) {
		MailMenuBar mailMenuBar = new MailMenuBar();
		mailMenuBar.setHeight("35px");
		PagedTable mailTable = new PagedTable();
		mailTable.setWidth("100%");
		mailTable.setHeight("100%");
		mailTable.setSelectable(true);
		mailTable.setMultiSelect(true);
		mailTable.setImmediate(true);
		mailTable.setColumnReorderingAllowed(true);
		mailTable.setColumnCollapsingAllowed(true);
		mailTable.setColumnWidth("All", 25);
		mailTable.setColumnWidth("Tag", 60);
		mailTable.setColumnWidth("Sender", 100);
		mailTable.setColumnWidth("Object", 200);
		mailTable.setColumnWidth("Date", 110);

		mailTable.setContainerDataSource(constructDataSource());
		addComponent(mailMenuBar);
		addComponent(mailTable);
	}

	@SuppressWarnings("unchecked")
	private Container constructDataSource() {
		IndexedContainer container = new IndexedContainer();
		/** TODO : ici ajouter un listener **/
		container.addContainerProperty("All", CheckBox.class, null);
		container.addContainerProperty("Tag", String.class, null);
		container.addContainerProperty("Sender", String.class, null);
		container.addContainerProperty("Object", String.class, null);
		container.addContainerProperty("Content", String.class, null);
		container.addContainerProperty("Date", String.class, null);
		int itemId = 0;
		Item item = container.addItem(itemId);
		item.getItemProperty("All").setValue(new CheckBox());
		item.getItemProperty("Tag").setValue("tag test");
		item.getItemProperty("Sender").setValue("sender test");
		item.getItemProperty("Object").setValue("object");
		item.getItemProperty("Content").setValue("contenu");
		item.getItemProperty("Date").setValue(dateFormat.format(new Date()));
		itemId++;
		item = container.addItem(itemId);
		CheckBox cb = new CheckBox();
		cb.addValueChangeListener(new MailSelectListener());
		item.getItemProperty("All").setValue(cb);
		item.getItemProperty("Tag").setValue("tag 2 test");
		item.getItemProperty("Sender").setValue("sender 2 test");
		item.getItemProperty("Object").setValue("object 2");
		item.getItemProperty("Content").setValue("contenu 2");
		return container;
	}

}