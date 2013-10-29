/**
 * 
 */
package fr.wati.scool.web.menu;


import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.stereotype.Component;

import com.vaadin.ui.NativeButton;

import fr.wati.scool.web.annotations.MenuConfig;
import fr.wati.scool.web.menu.Menu.MenuGroup;
import fr.wati.scool.web.view.DashBoardView;

/**
 * @author Rachid Ouattara
 *
 */
@Component
@Scope(SCOPE_PROTOTYPE)
@MenuConfig(MenuGroup.TOP)
@SuppressWarnings("serial")
public class DashboardMenu extends AbstractMenu {

	NativeButton nativeButton;
	

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.menu.Menu#getButton()
	 */
	@Override
	public NativeButton getComponent() {
		if(nativeButton==null){
			nativeButton=new NativeButton("DashBoard");
		}
		return nativeButton;
	}


	/* (non-Javadoc)
	 * @see fr.wati.scool.web.menu.Menu#hasSubMenu()
	 */
	@Override
	public boolean hasSubMenu() {
		return false;
	}


	@Override
	public String getViewName() {
		return DashBoardView.NAME;
	}
	
}
