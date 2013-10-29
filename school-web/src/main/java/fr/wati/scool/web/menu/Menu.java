/**
 * 
 */
package fr.wati.scool.web.menu;

import java.util.List;

import com.vaadin.ui.Component;

/**
 * @author Rachid Ouattara
 *
 */
public interface Menu{
	public enum MenuGroup{
		TOP,ADMIN;
	}
	Component getComponent();
	boolean hasSubMenu();
	List<Menu> getSubMenus();
	String getViewName();
}
