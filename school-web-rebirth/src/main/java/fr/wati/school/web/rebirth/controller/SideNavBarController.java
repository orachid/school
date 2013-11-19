/**
 * 
 */
package fr.wati.school.web.rebirth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.web.rebirth.commons.Layout;
import fr.wati.school.web.rebirth.commons.navigation.SideNavItemsFactory;
import fr.wati.school.web.rebirth.utils.ImprovedMustacheTemplateLoader;

/**
 * @author Rachid Ouattara
 *
 */
@Controller
public class SideNavBarController extends DefaultPageController {
	public static final String VIEW_NAME="/sidenavbar";
	@Autowired
    private ImprovedMustacheTemplateLoader improvedMustacheTemplateLoader;
	
	@Autowired
	private SideNavItemsFactory sideNavItemsFactory;
	
	@RequestMapping(value = VIEW_NAME,method=RequestMethod.POST)
	public ModelAndView render() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("layouts/partials/_shared/sidenav");
		super.processModelAndView(modelAndView);
//		Layout layout=new Layout();
//		layout.setSidenav_navList(sideNavItemsFactory.getSideNavItems());
//		modelAndView.addObject("layout", layout);
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.sidenav.shortcuts", "layouts/partials/_shared/sidenav/shortcuts");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.sidenav.items", "layouts/partials/_shared/sidenav/items");
		return modelAndView;
	} 

}
