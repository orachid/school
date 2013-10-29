/**
 * 
 */
package fr.wati.scool.web.components;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;

import fr.wati.util.SpringSecurityHelper;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Component
@Scope(SCOPE_PROTOTYPE)
public class HeaderHorizontalBar extends CustomComponent {

	
	private HorizontalLayout horizontalLayout=new HorizontalLayout();

	/**
	 * 
	 */
	public HeaderHorizontalBar() {
	}
	
	@PostConstruct
	public void build(){
		horizontalLayout.addStyleName("header-bar");
		Label logo = new Label("SCHOOL");
		horizontalLayout.addComponent(logo);
		horizontalLayout.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
		Label userName=new Label(SpringSecurityHelper.getUser().getUsername());
		horizontalLayout.addComponent(userName);
		horizontalLayout.setComponentAlignment(userName, Alignment.MIDDLE_RIGHT);
		Link logoutLink = new Link("Logout", new ExternalResource("/j_spring_security_logout"));
		horizontalLayout.addComponent(logoutLink);
		horizontalLayout.setComponentAlignment(logoutLink, Alignment.MIDDLE_RIGHT);
		setCompositionRoot(horizontalLayout);
	}
}
