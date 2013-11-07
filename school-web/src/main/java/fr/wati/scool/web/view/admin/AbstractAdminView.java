/**
 * 
 */
package fr.wati.scool.web.view.admin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar.MenuItem;

import fr.wati.scool.web.menu.AdminSideMenu;
import fr.wati.scool.web.view.AbstractView;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractAdminView extends AbstractView {

	@Autowired
	private transient ApplicationContext applicationContext;
	protected MenuItem editUser;
	protected MenuItem usersRight;
	private HorizontalLayout mainLayout=new HorizontalLayout();
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
		
		mainLayout.setSizeFull();
		AdminSideMenu adminSideMenu=applicationContext.getBean(AdminSideMenu.class);
		mainLayout.addComponent(adminSideMenu);
		mainLayout.setExpandRatio(adminSideMenu, 0.16f);
		Component contentComponent=getContent();
		mainLayout.addComponent(contentComponent);
		mainLayout.setExpandRatio(contentComponent, 1f);
		setCompositionRoot(mainLayout);
		

	}

	/**
	 * @return
	 */
	public abstract Component getContent();

}
