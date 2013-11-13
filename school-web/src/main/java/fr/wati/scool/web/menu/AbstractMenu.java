/**
 * 
 */
package fr.wati.scool.web.menu;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Button;
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
	public static List<AbstractMenu> menus=new ArrayList<>();
	/**
	 * 
	 */
	public AbstractMenu() {
		super();
		menus.add(this);
		if(getComponent() !=null && getComponent() instanceof Button){
			((Button)getComponent()).addClickListener(this);
			getComponent().addStyleName("borderless");
			
		}
		

	}

	/* (non-Javadoc)
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		for(Menu menu:menus){
			menu.getComponent().removeStyleName("selected");
		}
		event.getButton().getUI().getNavigator().navigateTo(getViewName());
		event.getButton().addStyleName("selected");
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
