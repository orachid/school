package fr.wati.school.web.rebirth.controller.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.web.rebirth.commons.Breadcrumbs;
import fr.wati.school.web.rebirth.commons.Breadcrumbs.Link;
import fr.wati.school.web.rebirth.commons.Layout;

@Controller
@RequestMapping("/publicdocuments")
public class PublicDocumentPageController extends AbstractDefaultPageController {

	public PublicDocumentPageController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView render() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = super.render(modelAndView);	
		//SideNav
		Layout layout=Layout.buildLayoutDefault();
		Layout.active("/publicdocuments");
		modelAndView.addObject("layout",layout );
		return modelAndView;
	}
	
	
	@Override
	public String getTitle() {
		return "Public documents";
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.view.AbstractDefaultPageController#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Browse public documents";
	}
	
	@Override
	public String getInline_scripts() {
		return "views/assets/scripts/classes-jqgrid.js";
	}

	@Override
	public String getContent() {
		return "/pages/publicdocuments";
	}

	@Override
	public Breadcrumbs getBreadcrumbs() {
		List<Link> links = new ArrayList<>();
		links.add(new Link("#", "Document"));
		Breadcrumbs breadcrumbs = new Breadcrumbs("Public documents", links);
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
