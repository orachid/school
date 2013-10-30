/**
 * 
 */
package fr.wati.scool.web.components;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

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
@Scope(value= WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.TARGET_CLASS)
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
