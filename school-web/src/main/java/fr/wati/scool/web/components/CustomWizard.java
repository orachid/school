/**
 * 
 */
package fr.wati.scool.web.components;

import org.vaadin.teemu.wizards.Wizard;

import com.vaadin.ui.HorizontalLayout;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
public class CustomWizard extends Wizard {

	public HorizontalLayout getFooter(){
		return footer;
	}
	
}
