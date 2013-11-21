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
	public String getTitle() {
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
	public String getDescription() {
		return "School WebAPP";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.wati.school.web.rebirth.controller.AbstractPageController#
	 * getInline_scripts()
	 */
	@Override
	public String getInline_scripts() {
		return "views/assets/scripts/login.js";
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.AbstractPageController#getResourceLoader()
	 */
	@Override
	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	/* (non-Javadoc)e
	 * @see fr.wati.school.web.rebirth.controller.AbstractPageController#getContent()
	 */
	@Override
	public String getContent() {
		return "views/pages/login";
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
