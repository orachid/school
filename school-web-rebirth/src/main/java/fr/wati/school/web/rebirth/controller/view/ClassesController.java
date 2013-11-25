package fr.wati.school.web.rebirth.controller.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.dao.ClasseRepository;
import fr.wati.school.services.ClasseService;
import fr.wati.school.web.rebirth.commons.Breadcrumbs;
import fr.wati.school.web.rebirth.commons.Breadcrumbs.Link;
import fr.wati.school.web.rebirth.commons.Layout;

@Controller
@RequestMapping("/classes")
public class ClassesController extends AbstractDefaultPageController {

	@Autowired
	private ClasseService classeService;
	@Autowired
	private ClasseRepository classeRepository;

	public ClassesController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView render() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = super.render(modelAndView);	
		//SideNav
		Layout layout=Layout.buildLayoutDefault();
		Layout.active("/classes");
		modelAndView.addObject("layout",layout );
		return modelAndView;
	}
	
	
	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.view.AbstractDefaultPageController#getTitle()
	 */
	@Override
	public String getTitle() {
		return "Classes edition";
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.view.AbstractDefaultPageController#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Create update delete classes ";
	}
	
	@Override
	public String getInline_scripts() {
		return "views/assets/scripts/classes-jqgrid.js";
	}

	@Override
	public String getContent() {
		return "/pages/crud/crud-edition";
	}

	@Override
	public Breadcrumbs getBreadcrumbs() {
		List<Link> links = new ArrayList<>();
		links.add(new Link("#", "Admin"));
		links.add(new Link("#", "Edition"));
		Breadcrumbs breadcrumbs = new Breadcrumbs("Classes edition", links);
		return breadcrumbs;
	}

	@Override
	public String[] getScripts() {
		return new String[] { "jqGrid/jquery.jqGrid.min.js",
				"jqGrid/i18n/grid.locale-en.js" };
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
		return new String[] { "jquery-ui-1.10.3.full.min.css", "ui.jqgrid.css",
				"datepicker.css" };
	}
}
