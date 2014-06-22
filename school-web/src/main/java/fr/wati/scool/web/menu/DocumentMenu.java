/**
 * 
 */
package fr.wati.scool.web.menu;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.vaadin.ui.Button;

import fr.wati.scool.web.annotations.MenuConfig;
import fr.wati.scool.web.menu.Menu.MenuGroup;
import fr.wati.scool.web.view.documents.DocumentView;
import fr.wati.util.IconProvider;

/**
 * @author Rachid Ouattara
 *
 */
@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
@MenuConfig(MenuGroup.TOP)
@SuppressWarnings("serial")
public class DocumentMenu extends AbstractMenu {

	private Button button;
	
	/* (non-Javadoc)
	 * @see fr.wati.scool.web.menu.Menu#getComponent()
	 */
	@Override
	public Button getComponent() {
		if(button==null){
			button=new Button("Documents");
			button.setHtmlContentAllowed(true);
			button.setIcon(IconProvider.getIcone32X32("folder.png"));
		}
		return button;
	}

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.menu.Menu#getViewName()
	 */
	@Override
	public String getViewName() {
		return DocumentView.NAME;
	}

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.menu.Menu#getPosition()
	 */
	@Override
	public int getPosition() {
		return 3;
	}

}
