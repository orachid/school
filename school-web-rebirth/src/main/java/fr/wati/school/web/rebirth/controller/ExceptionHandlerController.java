package fr.wati.school.web.rebirth.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.web.rebirth.commons.Breadcrumbs;
import fr.wati.school.web.rebirth.commons.Breadcrumbs.Link;
import fr.wati.school.web.rebirth.controller.view.AbstractDefaultPageController;

@ControllerAdvice
public class ExceptionHandlerController extends AbstractDefaultPageController {

 
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView=super.render(modelAndView);
		return modelAndView;
 
	}
	
	@Override
	public String getContent() {
		return "/pages/error-404";
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.view.AbstractDefaultPageController#getBreadcrumbs()
	 */
	@Override
	public Breadcrumbs getBreadcrumbs() {
		List<Link> links=new ArrayList<>();
		links.add(new Link("#", "Home"));
		Breadcrumbs breadcrumbs=new Breadcrumbs("Error", links);
		return breadcrumbs;
	}
	
	
}
