/**
 * 
 */
package fr.wati.scool.web.view;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;

import fr.wati.scool.web.NavigatorAware;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractView extends CustomComponent implements View,NavigatorAware {

	protected Navigator navigator;
	

	/* (non-Javadoc)
	 * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println("Enter View "+event.getViewName());

	}

	protected abstract void postConstruct();
	
	/**
	 * @return the navigator
	 */
	public Navigator getNavigator() {
		return navigator;
	}

	public void setNavigator(Navigator navigator) {
		this.navigator = navigator;
	}

	public abstract String getViewName();
}
