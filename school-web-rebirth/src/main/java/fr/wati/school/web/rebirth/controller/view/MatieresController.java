package fr.wati.school.web.rebirth.controller.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.dao.MatiereRepository;
import fr.wati.school.services.MatiereService;
import fr.wati.school.web.rebirth.commons.Breadcrumbs;
import fr.wati.school.web.rebirth.commons.Breadcrumbs.Link;
import fr.wati.school.web.rebirth.commons.Layout;

@Controller
@RequestMapping("/matieres")
public class MatieresController extends AbstractDefaultPageController {

	@Autowired
	private MatiereService matiereService;
	@Autowired
	private MatiereRepository matiereRepository;

	public MatieresController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView render() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = super.render(modelAndView);
//		improvedMustacheTemplateLoader.getIncludedTemplatePath().get()
//				.put("page.content", "/pages/crud/crud-edition");
		//modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("recordsUrl", "/matieres/records");
		modelAndView.addObject("addUrl", "/matieres/create");
		modelAndView.addObject("editUrl", "/matieres/update");
		modelAndView.addObject("deleteUrl", "/matieres/delete");
		
		//SideNav
		Layout layout=Layout.buildLayoutDefault();
		Layout.active("/matieres");
		modelAndView.addObject("layout",layout );
		return modelAndView;
	}
	
	@Override
	public String getTitle() {
		return "Matieres edition";
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.view.AbstractDefaultPageController#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Create update delete matieres";
	}

	@Override
	public String getInline_scripts() {
		return "views/assets/scripts/matieres-jqgrid.js";
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
		Breadcrumbs breadcrumbs = new Breadcrumbs("Matieres edition", links);
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
