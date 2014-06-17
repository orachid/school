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
@RequestMapping("/mydocuments")
public class MyDocumentsPageController extends AbstractDefaultPageController {

	public MyDocumentsPageController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView render() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = super.render(modelAndView);	
		//SideNav
		Layout layout=Layout.buildLayoutDefault();
		Layout.active("/mydocuments");
		modelAndView.addObject("layout",layout );
		return modelAndView;
	}
	
	@Override
	public String getTitle() {
		return "My documents";
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.view.AbstractDefaultPageController#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Manage my documents";
	}
	
	@Override
	public String getInline_scripts() {
		return "views/assets/scripts/documents.js,views/assets/scripts/elfinder.js";
	}

	@Override
	public String getContent() {
		return "/pages/mydocuments";
	}

	@Override
	public Breadcrumbs getBreadcrumbs() {
		List<Link> links = new ArrayList<>();
//		links.add(new Link("#", "Document"));
		Breadcrumbs breadcrumbs = new Breadcrumbs("Documents", links);
		return breadcrumbs;
	}

	@Override
	public String[] getScripts() {
		return new String[] { "jquery-ui-1.10.3.custom.min.js","fuelux/fuelux.tree.min.js","ace-elements.min.js","../elfinder/js/elfinder.full.js"};
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
		return new String[] {"../elfinder/css/elfinder.min.css","../elfinder/css/theme.css",
				"jquery-ui/themes/smoothness/jquery-ui.min.css"};
	}
}
