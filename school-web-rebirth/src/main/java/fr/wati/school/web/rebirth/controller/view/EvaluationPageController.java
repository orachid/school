/**
 * 
 */
package fr.wati.school.web.rebirth.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.web.rebirth.commons.Layout;

/**
 * @author Rachid Ouattara
 *
 */
@Controller
@RequestMapping(value = "/evaluation")
public class EvaluationPageController extends AbstractDefaultPageController {

	@RequestMapping
	public ModelAndView render() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = super.render(modelAndView);
		// SideNav
		Layout layout = Layout.buildLayoutDefault();
		Layout.active("/evaluation");
		modelAndView.addObject("layout", layout);
		return modelAndView;
	}

	@Override
	public String getContent() {
		return "/pages/evaluation";
	}

	@Override
	public String[] getScripts() {
		return new String[] {"jquery.tooltipster.min.js"};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.wati.school.web.rebirth.controller.AbstractDefaultPageController#getStyles
	 * ()
	 */
	@Override
	public String[] getStyles() {
		return new String[] {"tooltipster.css" };
	}

	@Override
	public String getInline_scripts() {
		return "views/assets/scripts/evaluation.js";
	}
	
}
