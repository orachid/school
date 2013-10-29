/**
 * 
 */
package fr.wati.scool.web.components;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
@Scope(SCOPE_PROTOTYPE)
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
		
		horizontalLayout.addStyleName("top-horizontal-menu-bar");
		List<Menu> topMenus = menuFactory.getTopMenus();
		for(Menu menu:topMenus){
			horizontalLayout.addComponent(menu.getComponent());
		}
		
		setCompositionRoot(horizontalLayout);
	}
	
}
