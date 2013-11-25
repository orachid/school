package fr.wati.school.web.rebirth.controller.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.dao.SalleRepository;
import fr.wati.school.services.SalleService;
import fr.wati.school.web.rebirth.commons.Breadcrumbs;
import fr.wati.school.web.rebirth.commons.Breadcrumbs.Link;
import fr.wati.school.web.rebirth.commons.Layout;

@Controller
@RequestMapping("/salles")
public class SallesController extends AbstractDefaultPageController {

	@Autowired
	private SalleService salleService;
	@Autowired
	private SalleRepository salleRepository;

	public SallesController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView render() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = super.render(modelAndView);
//		improvedMustacheTemplateLoader.getIncludedTemplatePath().get()
//				.put("page.content", "/pages/crud/crud-edition");
		//modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("recordsUrl", "/salles/records");
		modelAndView.addObject("addUrl", "/salles/create");
		modelAndView.addObject("editUrl", "/salles/update");
		modelAndView.addObject("deleteUrl", "/salles/delete");
		
		//SideNav
		Layout layout=Layout.buildLayoutDefault();
		Layout.active("/etablissements");
		modelAndView.addObject("layout",layout );
		return modelAndView;
	}
	
	@Override
	public String getTitle() {
		return "Salles edition";
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.view.AbstractDefaultPageController#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Create update delete salles ";
	}
	
	@Override
	public String getInline_scripts() {
		return "views/assets/scripts/salles-jqgrid.js";
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
		Breadcrumbs breadcrumbs = new Breadcrumbs("Salles edition", links);
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
