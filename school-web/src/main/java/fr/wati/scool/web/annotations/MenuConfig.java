/**
 * 
 */
package fr.wati.scool.web.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import fr.wati.scool.web.menu.Menu.MenuGroup;

/**
 * @author Rachid Ouattara
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MenuConfig {
	
	//The menu positioning
	MenuGroup value() default MenuGroup.TOP;
}
