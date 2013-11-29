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
@RequestMapping(value = "/absences")
public class AbsencePageController extends AbstractDefaultPageController {

	@RequestMapping
	public ModelAndView render() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = super.render(modelAndView);
		// SideNav
		Layout layout = Layout.buildLayoutDefault();
		Layout.active("/absences");
		modelAndView.addObject("layout", layout);
		return modelAndView;
	}
	
}
