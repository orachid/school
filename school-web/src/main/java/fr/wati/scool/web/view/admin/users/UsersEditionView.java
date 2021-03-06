/**
 * 
 */
package fr.wati.scool.web.view.admin.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Window;

import fr.wati.school.entities.bean.Users;
import fr.wati.scool.web.addons.ViewDescription;
import fr.wati.scool.web.components.CRUDPanelFactory;
import fr.wati.scool.web.components.DefaultCRUDPanel;
import fr.wati.scool.web.view.admin.AbstractAdminView;

/**
 * @author Rachid Ouattara
 * 
 */
@ViewDescription(name = UsersEditionView.NAME, requiredPermissions = "isAuthenticated()")
@SuppressWarnings("serial")
public class UsersEditionView extends AbstractAdminView {

	public static final String NAME = "usersEditionView";
	
	@Autowired
	private CRUDPanelFactory crudPanelFactory;
	
	@Autowired
	private transient ApplicationContext applicationContext;

	/**
	 * @param navigator
	 */
	public UsersEditionView() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.wati.scool.web.view.AbstractView#getViewName()
	 */
	@Override
	public String getViewName() {
		return NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.wati.scool.web.view.admin.AbstractAdminView#getContent()
	 */
	@Override
	public Component getContent() {
		final DefaultCRUDPanel<Users> usersCrudPanel=crudPanelFactory.getCRUDPanel(Users.class,"Utilisateurs","Edition des utilisateurs");
		usersCrudPanel.setAddClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				UserCreationView userCreationView=applicationContext.getBean(UserCreationView.class);
				final Window window=new Window("Create user wizard", userCreationView);
				userCreationView.setOnStepChangedRunnable(new Runnable() {
					@Override
					public void run() {
						window.center();
					}
				});
				userCreationView.setOnFinishRunnable(new Runnable() {
					@Override
					public void run() {
						usersCrudPanel.refresh();
						window.close();
					}
				});
				userCreationView.setOnCancelRunnable(new Runnable() {
					@Override
					public void run() {
						window.close();
					}
				});
				window.center();
				window.setModal(true);
				getUI().addWindow(window);
			}
		});
		return usersCrudPanel;
	}

}
