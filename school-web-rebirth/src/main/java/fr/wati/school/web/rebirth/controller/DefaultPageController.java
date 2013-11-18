/**
 * 
 */
package fr.wati.school.web.rebirth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.web.rebirth.commons.Breadcrumbs;
import fr.wati.school.web.rebirth.commons.Layout;
import fr.wati.school.web.rebirth.commons.navigation.SideNavItemsFactory;
import fr.wati.school.web.rebirth.utils.ImprovedMustacheTemplateLoader;

/**
 * @author Rachid Ouattara
 *
 */
@Controller
public class DefaultPageController extends AbstractPageController {

	@Autowired
    private ImprovedMustacheTemplateLoader improvedMustacheTemplateLoader;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private SideNavItemsFactory sideNavItemsFactory;
	
	@RequestMapping(value = "/")
	public ModelAndView login() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/layouts/default");
		super.processModelAndView(modelAndView);
		modelAndView.addObject("layout.topbar_tasks.count", 5);
		Breadcrumbs breadcrumbs=new Breadcrumbs();
		modelAndView.addObject("breadcrumbs", breadcrumbs);
		Layout layout=new Layout();
		layout.setSidenav_navList(sideNavItemsFactory.getSideNavItems());
		modelAndView.addObject("layout", layout);
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("page.content", "pages/blank");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.settings", "layouts/partials/_shared/settings");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout._template.jquery", "layouts/partials/_shared/_template/jquery");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar.user_menu", "layouts/partials/default/topbar/user_menu");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.sidenav.items", "layouts/partials/_shared/sidenav/items");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar", "layouts/partials/_shared/topbar");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar.tasks", "layouts/partials/_shared/topbar/tasks");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar.notifications", "layouts/partials/_shared/topbar/notifications");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar.messages", "layouts/partials/_shared/topbar/messages");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar.user_menu", "layouts/partials/default/topbar/user_menu");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout._template.fonts", "layouts/partials/_shared/_template/fonts");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.sidenav", "layouts/partials/_shared/sidenav");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.sidenav.shortcuts", "layouts/partials/_shared/sidenav/shortcuts");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.breadcrumbs", "layouts/partials/default/breadcrumbs");
		
		
		
		
		return modelAndView;
	}
	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.AbstractPageController#getResourceLoader()
	 */
	@Override
	ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.AbstractPageController#getTitle()
	 */
	@Override
	String getTitle() {
		return "Home page";
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.AbstractPageController#getDescription()
	 */
	@Override
	String getDescription() {
		return "School WebApp";
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.AbstractPageController#getInline_scripts()
	 */
	@Override
	String getInline_scripts() {
		return null;
	}
	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.AbstractPageController#getContent()
	 */
	@Override
	String getContent() {
		return "pages/index";
	}

}
