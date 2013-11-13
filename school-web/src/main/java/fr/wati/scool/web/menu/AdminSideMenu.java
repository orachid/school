/**
 * 
 */
package fr.wati.scool.web.menu;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;

import fr.wati.school.entities.bean.Role;
import fr.wati.scool.web.view.admin.AuditView;
import fr.wati.scool.web.view.admin.BatchEditionView;
import fr.wati.scool.web.view.admin.ClassesEditionView;
import fr.wati.scool.web.view.admin.EtablissementEditionView;
import fr.wati.scool.web.view.admin.MatieresEditionView;
import fr.wati.scool.web.view.admin.SalleEditionView;
import fr.wati.scool.web.view.admin.parameters.ConfigurationView;
import fr.wati.scool.web.view.admin.parameters.LogView;
import fr.wati.scool.web.view.admin.users.UsersEditionView;
import fr.wati.util.SpringSecurityHelper;

/**
 * @author Rachid Ouattara
 *
 */
@Component
@Scope(value= WebApplicationContext.SCOPE_SESSION)
@SuppressWarnings("serial")
public class AdminSideMenu extends CustomComponent implements ClickListener {

	private NativeButton userButton;
	private NativeButton rightManagementButton;
	private NativeButton classesButton;
	private NativeButton etablissementButton;
	private NativeButton batchButton;
	private NativeButton globalSettingsButton;
	private NativeButton messagesButton;
	private NativeButton auditButton;
	private NativeButton sallesButton;
	private Map<NativeButton, String> buttonMap=new HashMap<>();
	private NativeButton matieresButton;
	private NativeButton logsButton;

	/**
	 * @param caption
	 */
	public AdminSideMenu() {
		setImmediate(true);
	}

	@PostConstruct
	public void postConstruct(){
		if(SpringSecurityHelper.hasAnyRole(Role.ROLE_ADMIN,Role.ROLE_DIRECTOR)){

			VerticalLayout verticalLayout=new VerticalLayout();
			verticalLayout.addStyleName("sidebar-menu");
			
			Label editionLabel=new Label("Edition");
			verticalLayout.addComponent(editionLabel);
			
			userButton = new NativeButton("Users");
			userButton.addClickListener(this);
			buttonMap.put(userButton, UsersEditionView.NAME);
			verticalLayout.addComponent(userButton);
			
			rightManagementButton = new NativeButton("Gestion des droits");
			rightManagementButton.addClickListener(this);
			buttonMap.put(userButton, UsersEditionView.NAME);
			verticalLayout.addComponent(rightManagementButton);
			
			etablissementButton = new NativeButton("Etablissement");
			etablissementButton.addClickListener(this);
			buttonMap.put(etablissementButton, EtablissementEditionView.NAME);
			verticalLayout.addComponent(etablissementButton);
			
			classesButton = new NativeButton("Classes");
			classesButton.addClickListener(this);
			buttonMap.put(classesButton, ClassesEditionView.NAME);
			verticalLayout.addComponent(classesButton);
			
			matieresButton = new NativeButton("Matieres");
			matieresButton.addClickListener(this);
			buttonMap.put(matieresButton, MatieresEditionView.NAME);
			verticalLayout.addComponent(matieresButton);
			
			
			sallesButton = new NativeButton("Salles");
			sallesButton.addClickListener(this);
			buttonMap.put(sallesButton, SalleEditionView.NAME);
			verticalLayout.addComponent(sallesButton);
			
			batchButton = new NativeButton("Batch");
			batchButton.addClickListener(this);
			buttonMap.put(batchButton, BatchEditionView.NAME);
			verticalLayout.addComponent(batchButton);
			
			Label parametersLabel=new Label("Parameters");
			verticalLayout.addComponent(parametersLabel);
			
			globalSettingsButton = new NativeButton("Global settings");
			globalSettingsButton.addClickListener(this);
			buttonMap.put(globalSettingsButton, ConfigurationView.NAME);
			verticalLayout.addComponent(globalSettingsButton);
			
			messagesButton = new NativeButton("Messages");
			messagesButton.addClickListener(this);
			buttonMap.put(messagesButton, UsersEditionView.NAME);
			verticalLayout.addComponent(messagesButton);
			
			auditButton = new NativeButton("Audit");
			auditButton.addClickListener(this);
			buttonMap.put(auditButton, AuditView.NAME);;
			verticalLayout.addComponent(auditButton);
			
			logsButton = new NativeButton("Logs");
			logsButton.addClickListener(this);
			buttonMap.put(logsButton, LogView.NAME);;
			verticalLayout.addComponent(logsButton);
			
			
			setCompositionRoot(verticalLayout);
		}
		
	}

	
	public static class Node{
		private String caption;
		private String viewName;
		
		
		/**
		 * @param caption
		 * @param viewName
		 */
		public Node(String caption, String viewName) {
			super();
			this.caption = caption;
			this.viewName = viewName;
		}
		/**
		 * @return the caption
		 */
		public String getCaption() {
			return caption;
		}
		/**
		 * @param caption the caption to set
		 */
		public void setCaption(String caption) {
			this.caption = caption;
		}
		/**
		 * @return the viewName
		 */
		public String getViewName() {
			return viewName;
		}
		/**
		 * @param viewName the viewName to set
		 */
		public void setViewName(String viewName) {
			this.viewName = viewName;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return caption;
		}
			
	}

	/* (non-Javadoc)
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
	 */
	@Override
	
	public void buttonClick(ClickEvent event) {
		
		if (event!=null &&  event.getButton()!=null && buttonMap.get(event.getButton())!=null){
			Iterator<NativeButton> iterator = buttonMap.keySet().iterator();
			while(iterator.hasNext()){
				NativeButton button=iterator.next();
				button.removeStyleName("selected");
			}
			event.getComponent().getUI().getNavigator().navigateTo(buttonMap.get(event.getButton()));
			event.getButton().addStyleName("selected");
		}
	} 
}
