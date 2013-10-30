/**
 * 
 */
package fr.wati.scool.web.components;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

import fr.wati.scool.web.menu.Menu;
import fr.wati.scool.web.menu.MenuFactory;


/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Component
@Scope(value= WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TopHorizontalMenuBar extends CustomComponent {

	private HorizontalLayout horizontalLayout=new HorizontalLayout();
	@Autowired
	private MenuFactory menuFactory;
	/**
	 * @param navigator
	 */
	public TopHorizontalMenuBar() {
		super();
	}



	@PostConstruct
	public void build(){
		
		//horizontalLayout.addStyleName("top-horizontal-menu-bar");
		horizontalLayout.addStyleName("sidebar");
		
		List<Menu> topMenus = menuFactory.getTopMenus();
		for(Menu menu:topMenus){
			horizontalLayout.addComponent(menu.getComponent());
		}
		
		setCompositionRoot(horizontalLayout);
	}
	
}
