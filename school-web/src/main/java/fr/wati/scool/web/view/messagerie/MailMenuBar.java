package fr.wati.scool.web.view.messagerie;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class MailMenuBar extends HorizontalLayout{
	
	private static final String[] options = new String[] { "DÃ©placer", "Supprimer", "Signaler comme spam"};

	
	public MailMenuBar(){
		setMargin(new MarginInfo(false, false, false, true));
		setSpacing(true);
		setHeight("50px");
		Button btNew = new Button("Nouveau message");
		addComponent(btNew);
		ComboBox forSelection = new ComboBox();
		forSelection.setInputPrompt("Pour la sÃ©lection ...");
		for(String item: options){
			forSelection.addItem(item);
		}
		addComponent(forSelection);
		
		setComponentAlignment(btNew, Alignment.MIDDLE_CENTER);
		setComponentAlignment(forSelection, Alignment.MIDDLE_CENTER);
		
	}

}