package fr.wati.school.web.rebirth.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.web.rebirth.commons.Breadcrumbs;
import fr.wati.school.web.rebirth.commons.Breadcrumbs.Link;

@Controller
@RequestMapping(value = "/")
public class DefaultPageController extends AbstractDefaultPageController {

	public DefaultPageController() {
	}

	@RequestMapping
	public ModelAndView render() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView=super.render(modelAndView);
		return modelAndView;
	}

	@Override
	public Breadcrumbs getBreadcrumbs() {
		List<Link> links=new ArrayList<>();
		links.add(new Link("#", "Admin"));
		links.add(new Link("#", "Edition"));
		Breadcrumbs breadcrumbs=new Breadcrumbs("Default", links);
		return breadcrumbs;
	}
	
	
}
