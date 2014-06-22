/**
 * 
 */
package fr.wati.util;

import com.vaadin.ui.UI;

/**
 * @author Rachid Ouattara
 *
 */
public class SessionUtils {

	private SessionUtils(){}
	
	public static String getSessionId(UI ui){
		return ui.getSession().getSession().getId();
	}
}
