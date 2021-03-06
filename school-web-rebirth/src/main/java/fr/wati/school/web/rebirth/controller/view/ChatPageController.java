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
@RequestMapping("/chat")
public class ChatPageController extends AbstractDefaultPageController {

	public ChatPageController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView render() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = super.render(modelAndView);	
		//SideNav
		Layout layout=Layout.buildLayoutDefault();
		Layout.active("/chat");
		modelAndView.addObject("layout",layout );
		return modelAndView;
	}
	
	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.view.AbstractDefaultPageController#getTitle()
	 */
	@Override
	public String getTitle() {
		return "Chat";
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.view.AbstractDefaultPageController#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Talk with others";
	}
	
	@Override
	public String getInline_scripts() {
		return "views/assets/scripts/classes-jqgrid.js,views/assets/scripts/chat.js";
	}

	@Override
	public String getContent() {
		return "/pages/chat";
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
				"jqGrid/i18n/grid.locale-en.js","sockjs-0.3.4.js","stomp.js","jquery.gritter.min.js" };
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
		return new String[] { "jquery-ui.min.css", "ui.jqgrid.css",
				"datepicker.css","jquery.gritter.css" };
	}
}
