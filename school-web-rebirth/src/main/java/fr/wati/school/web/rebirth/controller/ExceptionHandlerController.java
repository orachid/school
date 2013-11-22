package fr.wati.school.web.rebirth.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

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
}
