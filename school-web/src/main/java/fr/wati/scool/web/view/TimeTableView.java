/**
 * 
 */
package fr.wati.scool.web.view;

import javax.annotation.PostConstruct;

import com.vaadin.ui.Calendar;

import fr.wati.scool.web.addons.ViewDescription;

/**
 * @author Rachid Ouattara
 *
 */
@ViewDescription(name = TimeTableView.NAME, requiredPermissions = "isAuthenticated()")
@SuppressWarnings("serial")
public class TimeTableView extends AbstractView {

	public static final String NAME="timetable"; 
	
	/**
	 * @param navigator
	 */
	public TimeTableView() {
		super();
	}

	@PostConstruct
	public void postConstruct(){
		setCompositionRoot(new Calendar("TimeTable"));
	}
	
	
	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.AbstractView#getViewName()
	 */
	@Override
	public String getViewName() {
		return NAME;
	}

	
}
