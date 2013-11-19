/**
 * 
 */
package fr.wati.school.web.rebirth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.web.rebirth.utils.ImprovedMustacheTemplateLoader;

/**
 * @author Rachid Ouattara
 *
 */
@Controller
public class TopBarMessagesController extends DefaultPageController {

	public static final String VIEW_NAME = "/topbarmessages";

	@Autowired
	private ImprovedMustacheTemplateLoader improvedMustacheTemplateLoader;

	@RequestMapping(value = VIEW_NAME, method = RequestMethod.POST)
	public ModelAndView render() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/layouts/partials/_shared/topbar/messages");
		super.processModelAndView(modelAndView);
		modelAndView.addObject("layout.topbar_messages.count", 5);

		// improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar.user_menu",
		// "layouts/partials/default/topbar/user_menu");
		// improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.sidenav.items",
		// "layouts/partials/_shared/sidenav/items");
		// improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.topbar",
		// "/topBar");
		// improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("layout.sidenav.shortcuts",
		// "layouts/partials/_shared/sidenav/shortcuts");
		return modelAndView;
	}
}
