/**
 * 
 */
package fr.wati.scool.web.menu;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;

import fr.wati.school.entities.bean.Role;
import fr.wati.scool.web.view.admin.parameters.ConfigurationView;
import fr.wati.scool.web.view.admin.users.UsersEditionView;
import fr.wati.scool.web.view.admin.users.UsersRightsManagementView;
import fr.wati.util.SpringSecurityHelper;

/**
 * @author Rachid Ouattara
 *
 */
@Component
@Scope(value= BeanDefinition.SCOPE_PROTOTYPE)
@SuppressWarnings("serial")
public class AdminTreeMenu extends Tree implements ItemClickListener {

	/**
	 * @param caption
	 */
	public AdminTreeMenu() {
		super("Admin");
		setImmediate(true);
	}

	@PostConstruct
	public void postConstruct(){
		if(SpringSecurityHelper.hasAllRoles(Role.ROLE_ADMIN)){
			addUserAdminNode();
			addParametersAdminNode();
		}
				
		addItemClickListener(this);
	}

	private void addUserAdminNode() {
		Node users=new Node("Utilisateurs", null);
		addItem(users);
		setChildrenAllowed(users, true);
		
		Node editionNode =new Node("Edition", UsersEditionView.NAME);
		addItem(editionNode);
		setChildrenAllowed(editionNode, false);
		setParent(editionNode, users);
		
		Node rightManagementNode =new Node("Gestion des droits", UsersRightsManagementView.NAME);
		addItem(rightManagementNode);
		setChildrenAllowed(rightManagementNode, false);
		setParent(rightManagementNode, users);
		expandItemsRecursively(users);
	}
	
	private void addParametersAdminNode() {
		Node parametersNode=new Node("Parametres", null);
		addItem(parametersNode);
		setChildrenAllowed(parametersNode, true);
		Node globalSettingsNode = new Node("Global settings", ConfigurationView.NAME);
		addItem(globalSettingsNode);
		setChildrenAllowed(globalSettingsNode, false);
		setParent(globalSettingsNode, parametersNode);
		Node messagesNode = new Node("Messages", UsersRightsManagementView.NAME);
		addItem(messagesNode);
		setChildrenAllowed(messagesNode, false);
		setParent(messagesNode, parametersNode);
		expandItemsRecursively(parametersNode);
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
	 * @see com.vaadin.event.ItemClickEvent.ItemClickListener#itemClick(com.vaadin.event.ItemClickEvent)
	 */
	@Override
	public void itemClick(ItemClickEvent event) {
		if (event!=null &&  event.getButton() == ItemClickEvent.BUTTON_LEFT && event.getItemId() instanceof Node){
			Node selectedNode=((Node)event.getItemId());
			if(StringUtils.isNotEmpty(selectedNode.getViewName())){
				event.getComponent().getUI().getNavigator().navigateTo(selectedNode.getViewName());
			}
			
		}

	} 
}
