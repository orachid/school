/**
 * 
 */
package fr.wati.scool.web.menu;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
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
@Scope(SCOPE_PROTOTYPE)
public class MenuFactory implements ApplicationContextAware {

	private ApplicationContext applicationContext;

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
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
				this.applicationContext = applicationContext;
	}
}
