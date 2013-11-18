/**
 * 
 */
package fr.wati.school.web.rebirth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.web.rebirth.utils.ImprovedMustacheTemplateLoader;

/**
 * @author Rachid Ouattara
 * 
 */
@Controller
public class LoginController extends AbstractPageController {

	@Autowired
    private ImprovedMustacheTemplateLoader improvedMustacheTemplateLoader;
	
	@Autowired
	private ResourceLoader resourceLoader;

	@RequestMapping(value = "/login")
	public ModelAndView login() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/layouts/login");
		super.processModelAndView(modelAndView);
		modelAndView.addObject("pageContent", "pages/login");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("pageContent", "pages/login");
		return modelAndView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.wati.school.web.rebirth.controller.AbstractPageController#getTitle()
	 */
	@Override
	String getTitle() {
		return "Login page";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.wati.school.web.rebirth.controller.AbstractPageController#getDescription
	 * ()
	 */
	@Override
	String getDescription() {
		return "School WebAPP";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.wati.school.web.rebirth.controller.AbstractPageController#
	 * getInline_scripts()
	 */
	@Override
	String getInline_scripts() {
		return "views/assets/scripts/login.js";
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.AbstractPageController#getResourceLoader()
	 */
	@Override
	ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	/* (non-Javadoc)e
	 * @see fr.wati.school.web.rebirth.controller.AbstractPageController#getContent()
	 */
	@Override
	String getContent() {
		return "views/pages/login";
	}
}
