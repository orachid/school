package fr.wati.school.web.rebirth.controller.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.web.rebirth.commons.Breadcrumbs;
import fr.wati.school.web.rebirth.commons.Layout;
import fr.wati.school.web.rebirth.commons.Breadcrumbs.Link;

@Controller
@RequestMapping(value = "/")
public class DefaultPageController extends AbstractDefaultPageController {

	public DefaultPageController() {
	}

	@RequestMapping
	public ModelAndView render() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = super.render(modelAndView);
		// SideNav
		Layout layout = Layout.buildLayoutDefault();
		Layout.active("/");
		modelAndView.addObject("layout", layout);
		return modelAndView;
	}

	@Override
	public Breadcrumbs getBreadcrumbs() {
		List<Link> links = new ArrayList<>();
		links.add(new Link("#", "Home"));
		Breadcrumbs breadcrumbs = new Breadcrumbs("Home", links);
		return breadcrumbs;
	}

}
