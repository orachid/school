/**
 * 
 */
package fr.wati.scool.web.components;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;

import fr.wati.scool.web.menu.Menu;
import fr.wati.scool.web.menu.MenuFactory;


/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@Component
@Scope(value= BeanDefinition.SCOPE_PROTOTYPE)
public class TopHorizontalMenuBar extends CustomComponent {

	private CssLayout cssLayout=new CssLayout();
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
		cssLayout.addStyleName("toolbar");
		
		List<Menu> topMenus = menuFactory.getTopMenus();
		for(Menu menu:topMenus){
			cssLayout.addComponent(menu.getComponent());
		}
		
		setCompositionRoot(cssLayout);
	}
	
}
