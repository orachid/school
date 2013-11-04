/**
 * 
 */
package fr.wati.scool.web.menu;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.vaadin.ui.NativeButton;

import fr.wati.scool.web.annotations.MenuConfig;
import fr.wati.scool.web.menu.Menu.MenuGroup;
import fr.wati.scool.web.view.TimeTableView;

/**
 * @author Rachid Ouattara
 *
 */
@Component
@Scope(value= WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.TARGET_CLASS)
@MenuConfig(MenuGroup.TOP)
@SuppressWarnings("serial")
public class TimeTableMenu extends AbstractMenu {

	private NativeButton nativeButton;
	/**
	 * @param navigator
	 */
	public TimeTableMenu() {
		super();
	}

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.menu.Menu#getButton()
	 */
	@Override
	public NativeButton getComponent() {
		if(nativeButton==null){
			nativeButton=new NativeButton("Calendrier");
			nativeButton.setHtmlContentAllowed(true);
		}
		return nativeButton;
	}

	@Override
	public String getViewName() {
		return TimeTableView.NAME;
	}

}
