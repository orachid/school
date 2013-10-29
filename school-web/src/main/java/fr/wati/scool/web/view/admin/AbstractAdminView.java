/**
 * 
 */
package fr.wati.scool.web.view.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.MenuBar.MenuItem;

import fr.wati.scool.web.addons.SpringSecurityViewProvider;
import fr.wati.scool.web.menu.AdminTreeMenu;
import fr.wati.scool.web.view.AbstractView;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractAdminView extends AbstractView {

	protected MenuItem editUser;
	protected MenuItem usersRight;
	private HorizontalSplitPanel mainLayout;
	/**
	 * @param navigator
	 */
	public AbstractAdminView() {
		super();
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.AbstractView#postConstruct()
	 */
	@PostConstruct
	@Override
	protected void postConstruct() {
		mainLayout=new HorizontalSplitPanel();
		//mainLayout.setSizeFull();
		mainLayout.addComponent(SpringSecurityViewProvider.applicationContext.getBean(AdminTreeMenu.class));
		setCompositionRoot(mainLayout);
		mainLayout.addComponent(getContent());
		mainLayout.setSplitPosition(20);
	}



	/**
	 * @return
	 */
	public abstract Component getContent();

	
	

//	protected  MenuBar buildMenuBar(){
//		MenuBar menuBar=new MenuBar();
//		//Users
//		MenuItem users=menuBar.addItem("Utilisateurs", null, null);
//		editUser = users.addItem("Edition utilisateurs", null, this);
//		menuViewMap.put(editUser, UsersEditionView.NAME);
//		usersRight = users.addItem("Gestion des droits", null, this);
//		menuViewMap.put(usersRight, UsersRightsManagementView.NAME);
//		//parameters
//		MenuItem parameters=menuBar.addItem("Parametres", null, null);
//		configuration = parameters.addItem("Configuration", null, this);
//		menuViewMap.put(configuration, ConfigurationView.NAME);
//		logs = parameters.addItem("Logs", null, this);
//		menuViewMap.put(logs, LogView.NAME);
//		
//		return menuBar;
//	}


}
