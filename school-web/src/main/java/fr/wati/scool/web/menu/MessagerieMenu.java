/**
 * 
 */
package fr.wati.scool.web.menu;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.vaadin.ui.Button;

import fr.wati.scool.web.annotations.MenuConfig;
import fr.wati.scool.web.menu.Menu.MenuGroup;
import fr.wati.scool.web.view.messagerie.MessagerieView;

/**
 * @author Rachid Ouattara
 *
 */
@Component
@Scope(value= WebApplicationContext.SCOPE_SESSION)
@MenuConfig(MenuGroup.TOP)
@SuppressWarnings("serial")
public class MessagerieMenu extends AbstractMenu {

	private Button button;
	
	/* (non-Javadoc)
	 * @see fr.wati.scool.web.menu.Menu#getComponent()
	 */
	@Override
	public Button getComponent() {
		if(button==null){
			button=new Button("Messagerie");
			button.setHtmlContentAllowed(true);
		}
		return button;
	}

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.menu.Menu#getViewName()
	 */
	@Override
	public String getViewName() {
		return MessagerieView.NAME;
	}

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.menu.Menu#getPosition()
	 */
	@Override
	public int getPosition() {
		return 4;
	}

}
