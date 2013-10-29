/**
 * 
 */
package fr.wati.scool.web.menu;

import java.util.List;

import com.vaadin.ui.NativeButton;

/**
 * @author Rachid Ouattara
 *
 */
public interface Menu{
	public static final String TOP="TOP";

	NativeButton getButton();
	boolean hasSubMenu();
	List<Menu> getSubMenus();
	String getViewName();
}
