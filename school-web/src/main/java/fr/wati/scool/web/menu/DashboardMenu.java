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
import fr.wati.scool.web.view.DashBoardView;

/**
 * @author Rachid Ouattara
 *
 */
@Component
@Scope(value= WebApplicationContext.SCOPE_SESSION)
@MenuConfig(MenuGroup.TOP)
@SuppressWarnings("serial")
public class DashboardMenu extends AbstractMenu {

	Button button;
	

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.menu.Menu#getButton()
	 */
	@Override
	public Button getComponent() {
		if(button==null){
			button=new Button("DashBoard");
			button.setHtmlContentAllowed(true);
		}
		return button;
	}

	@Override
	public String getViewName() {
		return DashBoardView.NAME;
	}


	@Override
	public int getPosition() {
		return 0;
	}
	
}
