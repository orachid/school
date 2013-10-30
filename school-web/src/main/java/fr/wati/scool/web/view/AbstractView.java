/**
 * 
 */
package fr.wati.scool.web.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractView extends CustomComponent implements View {

	/* (non-Javadoc)
	 * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println("Enter View "+event.getViewName());

	}

	protected abstract void postConstruct();
	
	public abstract String getViewName();
}
