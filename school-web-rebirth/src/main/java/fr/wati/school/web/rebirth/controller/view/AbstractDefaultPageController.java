/**
 * 
 */
package fr.wati.school.web.rebirth.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.web.rebirth.commons.Breadcrumbs;
import fr.wati.school.web.rebirth.commons.Layout;
import fr.wati.school.web.rebirth.commons.navigation.SideNavItemsFactory;
import fr.wati.school.web.rebirth.utils.ImprovedMustacheTemplateLoader;
import fr.wati.school.web.rebirth.utils.SecurityUtils;

/**
 * @author Rachid Ouattara
 *
 */

public class AbstractDefaultPageController extends AbstractPageController {

	@Autowired
    private ImprovedMustacheTemplateLoader improvedMustacheTemplateLoader;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private SideNavItemsFactory sideNavItemsFactory;
	
	
	public ModelAndView render(ModelAndView modelAndView) {
		modelAndView.setViewName("/layouts/default");
		super.processModelAndView(modelAndView);
		modelAndView.addObject("layout.topbar_tasks.count", 5);
		modelAndView.addObject("user_welcome_name", SecurityUtils.getConnectedUser().getPrenom());
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("page.content", getContent());
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.settings", "layouts/partials/_shared/settings");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout._template.jquery", "layouts/partials/_shared/_template/jquery");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar.user_menu", "layouts/partials/default/topbar/user_menu");
		//improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar", TopBarController.VIEW_NAME);
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar", "/layouts/partials/_shared/topbar");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar.tasks", "layouts/partials/_shared/topbar/tasks");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar.notifications", "layouts/partials/_shared/topbar/notifications");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar.messages", "layouts/partials/_shared/topbar/messages");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout._template.fonts", "layouts/partials/_shared/_template/fonts");
		//improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layoutsidenav", SideNavBarController.VIEW_NAME);
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.sidenav", "layouts/partials/_shared/sidenav");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.breadcrumbs", "layouts/partials/default/breadcrumbs");
		
		//TopBar
		
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar.user_menu", "layouts/partials/default/topbar/user_menu");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.sidenav.items", "layouts/partials/_shared/sidenav/items");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.sidenav.shortcuts", "layouts/partials/_shared/sidenav/shortcuts");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar.tasks", "/layouts/partials/_shared/topbar/tasks");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar.notifications", "/layouts/partials/_shared/topbar/notifications");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar.messages", "/layouts/partials/_shared/topbar/messages");
		
		//TopBarTask
		modelAndView.addObject("layouttopbar_taskscount", 5);
		modelAndView.addObject("layouttopbar_notificationscount", 5);
		modelAndView.addObject("layouttopbar_messagescount", 5);
		
		//SideNav
		modelAndView.addObject("layout", Layout.buildLayoutDefault());
		
		return modelAndView;
	}
	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.AbstractPageController#getResourceLoader()
	 */
	@Override
	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.AbstractPageController#getTitle()
	 */
	@Override
	public String getTitle() {
		return "Home page";
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.AbstractPageController#getDescription()
	 */
	@Override
	public String getDescription() {
		return "School WebApp";
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.AbstractPageController#getInline_scripts()
	 */
	@Override
	public String getInline_scripts() {
		return null;
	}
	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.AbstractPageController#getContent()
	 */
	@Override
	public String getContent() {
		return "pages/index";
	}
	@Override
	public Breadcrumbs getBreadcrumbs() {
		return null;
	}
	@Override
	public String[] getScripts() {
		return null;
	}
	@Override
	public String[] getIe_scripts() {
		return null;
	}
	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.AbstractPageController#getStyles()
	 */
	@Override
	public String[] getStyles() {
		return null;
	}

}
