/**
 * 
 */
package fr.wati.scool.web.menu;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.vaadin.ui.NativeButton;

import fr.wati.school.entities.bean.Role;
import fr.wati.scool.web.annotations.MenuConfig;
import fr.wati.scool.web.menu.Menu.MenuGroup;
import fr.wati.scool.web.view.admin.AdminView;

/**
 * @author Rachid Ouattara
 *
 */
@Component
@Scope(SCOPE_PROTOTYPE)
@Secured({Role.ROLE_ADMIN,Role.ROLE_DIRECTOR})
@MenuConfig(MenuGroup.TOP)
@SuppressWarnings("serial")
public class AdminMenu extends AbstractMenu {

	private NativeButton nativeButton;

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.menu.Menu#getButton()
	 */
	@Override
	public NativeButton getComponent() {
		if(nativeButton==null){
			nativeButton=new NativeButton("Admin");
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
		return AdminView.NAME;
	}

}
