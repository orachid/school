/**
 * 
 */
package fr.wati.school.web.rebirth.controller.view;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView login(@RequestParam(value="error",defaultValue="false",required=false) boolean error,HttpSession httpSession) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("layouts/login");
		super.processModelAndView(modelAndView);
		modelAndView.addObject("pageContent", "pages/login");
		if(error){
			modelAndView.addObject("error", true);
			String exeptionMessage=null;
			if(httpSession!=null){
				if(httpSession.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") instanceof Exception){
					exeptionMessage=((Exception)httpSession.getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage();
					modelAndView.addObject("errorMessage", exeptionMessage);
				}
				
			}
		}
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("pageContent", "pages/login");
		return modelAndView;
	}

	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelAndView modelAndView) {
		modelAndView.addObject("error", "true");
		modelAndView.addObject("errorMessage", "true");
		return "login";
 
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
