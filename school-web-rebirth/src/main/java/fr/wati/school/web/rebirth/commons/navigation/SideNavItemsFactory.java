/**
 * 
 */
package fr.wati.school.web.rebirth.commons.navigation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import fr.wati.school.web.rebirth.utils.SecurityUtils;

/**
 * @author Rachid Ouattara
 *
 */
@Component
public class SideNavItemsFactory {
	
	
	public List<SideNavItem> getSideNavItems(){
		List<SideNavItem> items=new ArrayList<>();
		items.add((SideNavItem) SecurityUtils.applicationContext.getBean("dashBoard"));
		items.add((SideNavItem) SecurityUtils.applicationContext.getBean("admin"));
		return items;
	}

}
