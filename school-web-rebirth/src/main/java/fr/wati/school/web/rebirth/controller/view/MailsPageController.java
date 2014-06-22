package fr.wati.school.web.rebirth.controller.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.web.rebirth.commons.Breadcrumbs;
import fr.wati.school.web.rebirth.commons.Breadcrumbs.Link;
import fr.wati.school.web.rebirth.commons.Layout;
import fr.wati.school.web.rebirth.utils.ImprovedMustacheTemplateLoader;

@Controller
@RequestMapping("/mails")
public class MailsPageController extends AbstractDefaultPageController {

	@Autowired
    private ImprovedMustacheTemplateLoader improvedMustacheTemplateLoader;
	
	public MailsPageController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView render() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = super.render(modelAndView);	
		//SideNav
		Layout layout=Layout.buildLayoutDefault();
		Layout.active("/mails");
		modelAndView.addObject("layout",layout );
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("page.tabs", "pages/partials/inbox/tabs");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("page.navbar", "pages/partials/inbox/navbar");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("page.message_actions", "pages/partials/inbox/message_actions");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("page.message_content", "pages/partials/inbox/message_content");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("page.message_footer", "pages/partials/inbox/message_footer");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("page.message_select", "pages/partials/inbox/message_select");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("page.message_sort", "pages/partials/inbox/message_sort");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("page.message_list", "pages/partials/inbox/message_list");
		improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("page.message_form", "pages/partials/inbox/message_form");
		return modelAndView;
	}
	
	
	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.view.AbstractDefaultPageController#getTitle()
	 */
	@Override
	public String getTitle() {
		return "Mails";
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.view.AbstractDefaultPageController#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Read Write your mails";
	}

	@Override
	public String getInline_scripts() {
		return "views/assets/scripts/classes-jqgrid.js";
	}

	@Override
	public String getContent() {
		return "/pages/mails";
	}

	@Override
	public Breadcrumbs getBreadcrumbs() {
		List<Link> links = new ArrayList<>();
		links.add(new Link("#", "Messagerie"));
		Breadcrumbs breadcrumbs = new Breadcrumbs("Mails", links);
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
