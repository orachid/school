/**
 * 
 */
package fr.wati.scool.web.menu;

import java.util.List;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import fr.wati.scool.web.view.AbstractView;

/**
 * @author Rachid Ouattara
 * 
 */
@SuppressWarnings("serial")
public abstract class AbstractMenu implements Menu, ClickListener {

	protected AbstractView abstractView;
	/**
	 * 
	 */
	public AbstractMenu() {
		super();
		getButton().addClickListener(this);

	}

	/* (non-Javadoc)
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		if(!hasSubMenu()){//no submenu to show, display view
			event.getButton().getUI().getNavigator().navigateTo(getViewName());
		}
	}

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.menu.Menu#getSubMenus()
	 */
	@Override
	public List<Menu> getSubMenus() {
		throw new UnsupportedOperationException("This method should be overiden");
	}

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.menu.Menu#hasSubMenu()
	 */
	@Override
	public boolean hasSubMenu() {
		return false;
	}	
	
}
