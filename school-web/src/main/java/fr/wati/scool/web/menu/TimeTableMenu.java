/**
 * 
 */
package fr.wati.scool.web.menu;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.ui.NativeButton;

import fr.wati.scool.web.annotations.MenuConfig;
import fr.wati.scool.web.menu.Menu.MenuGroup;
import fr.wati.scool.web.view.TimeTableView;

/**
 * @author Rachid Ouattara
 *
 */
@Component
@Scope(SCOPE_PROTOTYPE)
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
			nativeButton=new NativeButton("Emploie de temps");
		}
		return nativeButton;
	}

	@Override
	public String getViewName() {
		return TimeTableView.NAME;
	}

}
