/**
 * 
 */
package fr.wati.scool.web.view.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

import fr.wati.scool.web.view.AbstractView;
import fr.wati.scool.web.view.HasMenuBar;
import fr.wati.scool.web.view.admin.parameters.ConfigurationView;
import fr.wati.scool.web.view.admin.parameters.LogView;
import fr.wati.scool.web.view.admin.users.UsersEditionView;
import fr.wati.scool.web.view.admin.users.UsersRightsManagementView;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractAdminView extends AbstractView implements HasMenuBar,MenuBar.Command {

	protected MenuItem editUser;
	protected MenuItem usersRight;
	protected MenuBar menuBar;
	private Map<MenuItem,String > menuViewMap=new HashMap<>();
	private MenuItem configuration;
	private MenuItem logs;
	private VerticalLayout mainLayout;
	/**
	 * @param navigator
	 */
	public AbstractAdminView() {
		super();
		mainLayout=new VerticalLayout();
		//springify this
		menuBar=buildMenuBar();
		mainLayout.addComponent(menuBar);
		setCompositionRoot(mainLayout);
	}
	
	
	
	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.AbstractView#postConstruct()
	 */
	@PostConstruct
	@Override
	protected void postConstruct() {
		mainLayout.addComponent(getContent());
	}



	/**
	 * @return
	 */
	public abstract Component getContent();

	
	/**
	 * @return the menuBar
	 */
	public MenuBar getMenuBar() {
		return menuBar;
	}
	/**
	 * @param menuBar the menuBar to set
	 */
	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}

	protected  MenuBar buildMenuBar(){
		MenuBar menuBar=new MenuBar();
		//Users
		MenuItem users=menuBar.addItem("Utilisateurs", null, null);
		editUser = users.addItem("Edition utilisateurs", null, this);
		menuViewMap.put(editUser, UsersEditionView.NAME);
		usersRight = users.addItem("Gestion des droits", null, this);
		menuViewMap.put(usersRight, UsersRightsManagementView.NAME);
		//parameters
		MenuItem parameters=menuBar.addItem("Parametres", null, null);
		configuration = parameters.addItem("Configuration", null, this);
		menuViewMap.put(configuration, ConfigurationView.NAME);
		logs = parameters.addItem("Logs", null, this);
		menuViewMap.put(logs, LogView.NAME);
		
		return menuBar;
	}

	@Override
	public void menuSelected(MenuItem selectedItem) {
		menuBar.getUI().getNavigator().navigateTo(menuViewMap.get(selectedItem));
	}
}
