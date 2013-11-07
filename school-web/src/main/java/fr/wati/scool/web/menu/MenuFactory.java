/**
 * 
 */
package fr.wati.scool.web.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import fr.wati.scool.web.annotations.MenuConfig;
import fr.wati.scool.web.menu.Menu.MenuGroup;
import fr.wati.util.SpringSecurityHelper;

/**
 * @author Rachid Ouattara
 * 
 */
@Component
public class MenuFactory{

	@Autowired
	private transient ApplicationContext applicationContext;

	public  List<Menu> getTopMenus() {
		return getMenus(MenuGroup.TOP);
	}
	
	public  List<Menu> getAdminMenus() {
		return getMenus(MenuGroup.ADMIN);
	}
	
	
	public List<Menu> getMenus(MenuGroup menuGroup){
		List<Menu> menus = new ArrayList<>();
		String[] menuBeanNames = applicationContext.getBeanNamesForType(Menu.class);
		
		for(String beanName:menuBeanNames){
			MenuConfig menuConfig=null;
			Object bean = applicationContext.getBean(beanName);
			Class<?> targetClass = AopUtils.getTargetClass(bean);
			if(targetClass.isAnnotationPresent(MenuConfig.class)){
					if(targetClass.isAnnotationPresent(Secured.class)){
							Secured secured=targetClass.getAnnotation(Secured.class);
							if(SpringSecurityHelper.hasAnyRole(secured.value())){
								menuConfig=targetClass.getAnnotation(MenuConfig.class);
								if(menuGroup.equals(menuConfig.value())){
									menus.add((Menu) bean);
								}
							} 
					}else {
						menuConfig=targetClass.getAnnotation(MenuConfig.class);
						if(menuGroup.equals(menuConfig.value())){
							menus.add((Menu) bean);
						}
					}
			}
		}
		return menus;
	}

}
