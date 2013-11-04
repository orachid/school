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
import fr.wati.scool.web.view.admin.AuditView;
import fr.wati.scool.web.view.admin.BatchEditionView;
import fr.wati.scool.web.view.admin.ClassesEditionView;
import fr.wati.scool.web.view.admin.EtablissementEditionView;
import fr.wati.scool.web.view.admin.MatieresEditionView;
import fr.wati.scool.web.view.admin.SalleEditionView;
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
		if(SpringSecurityHelper.hasAnyRole(Role.ROLE_ADMIN,Role.ROLE_DIRECTOR)){
			//Edition
			Node editionNode=new Node("Edition", null);
			addItem(editionNode);
			setChildrenAllowed(editionNode, true);
				//Users
			Node usersNode =new Node("Users", UsersEditionView.NAME);
			addItem(usersNode);
			setChildrenAllowed(usersNode, false);
			setParent(usersNode, editionNode);
				//Right management
			Node rightManagementNode =new Node("Gestion des droits", UsersRightsManagementView.NAME);
			addItem(rightManagementNode);
			setChildrenAllowed(rightManagementNode, false);
			setParent(rightManagementNode, editionNode);
				//Classe
			Node classesNode =new Node("Classes", ClassesEditionView.NAME);
			addItem(classesNode);
			setChildrenAllowed(classesNode, false);
			setParent(classesNode, editionNode);
				//Matieres
			Node matieresNode =new Node("Matieres", MatieresEditionView.NAME);
			addItem(matieresNode);
			setChildrenAllowed(matieresNode, false);
			setParent(matieresNode, editionNode);
				//Salles
			Node sallesNode =new Node("Salles", SalleEditionView.NAME);
			addItem(sallesNode);
			setChildrenAllowed(sallesNode, false);
			setParent(sallesNode, editionNode);
				//Etablissement
			Node etablissementNode =new Node("Etablissement", EtablissementEditionView.NAME);
			addItem(etablissementNode);
			setChildrenAllowed(etablissementNode, false);
			setParent(etablissementNode, editionNode);
				//Batch
			Node batchNode =new Node("Batch", BatchEditionView.NAME);
			addItem(batchNode);
			setChildrenAllowed(batchNode, false);
			setParent(batchNode, editionNode);
			expandItemsRecursively(editionNode);
			
			//Parametres
			Node parametersNode=new Node("Parametres", null);
			addItem(parametersNode);
			setChildrenAllowed(parametersNode, true);
				//Global settings
			Node globalSettingsNode = new Node("Global settings", ConfigurationView.NAME);
			addItem(globalSettingsNode);
			setChildrenAllowed(globalSettingsNode, false);
			setParent(globalSettingsNode, parametersNode);
				//Messages
			Node messagesNode = new Node("Messages", UsersRightsManagementView.NAME);
			addItem(messagesNode);
			setChildrenAllowed(messagesNode, false);
			setParent(messagesNode, parametersNode);
				//Audit
			Node auditNode = new Node("Audit", AuditView.NAME);
			addItem(auditNode);
			setChildrenAllowed(auditNode, false);
			setParent(auditNode, parametersNode);
			
			expandItemsRecursively(parametersNode);
		}
				
		addItemClickListener(this);
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
	@SuppressWarnings("deprecation")
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
