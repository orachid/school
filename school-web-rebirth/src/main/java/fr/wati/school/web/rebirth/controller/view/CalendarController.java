package fr.wati.school.web.rebirth.controller.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.web.rebirth.commons.Breadcrumbs;
import fr.wati.school.web.rebirth.commons.Layout;
import fr.wati.school.web.rebirth.commons.Breadcrumbs.Link;

@Controller
@RequestMapping(value = "/calendar")
public class CalendarController extends AbstractDefaultPageController {

	public CalendarController() {
	}

	@RequestMapping
	public ModelAndView render() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = super.render(modelAndView);
		// SideNav
		Layout layout = Layout.buildLayoutDefault();
		Layout.active("/calendar");
		modelAndView.addObject("layout", layout);
		return modelAndView;
	}

	@Override
	public String getTitle() {
		return "Agenda";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.wati.school.web.rebirth.controller.view.AbstractDefaultPageController
	 * #getDescription()
	 */
	@Override
	public String getDescription() {
		return "Follow event";
	}

	@Override
	public Breadcrumbs getBreadcrumbs() {
		List<Link> links = new ArrayList<>();
		links.add(new Link("#", "Admin"));
		links.add(new Link("#", "Edition"));
		Breadcrumbs breadcrumbs = new Breadcrumbs("Default", links);
		return breadcrumbs;
	}

	@Override
	public String getInline_scripts() {
		return "views/assets/scripts/calendar.js,views/assets/scripts/evenement.js,views/assets/scripts/referentials.js";
	}

	@Override
	public String getContent() {
		return "/pages/calendar";
	}

	@Override
	public String[] getScripts() {
		return new String[] { "jquery-ui-1.10.3.custom.min.js",
				"jquery.ui.touch-punch.min.js", "fullcalendar.min.js",
				"bootbox.min.js", "date-time/daterangepicker.min.js",
				"chosen.jquery.min.js",
				"date-time/bootstrap-datepicker.min.js",
				"date-time/bootstrap-timepicker.min.js",
				"date-time/moment.min.js", "ajax-chosen.js",
				"bootstrap-datetimepicker.min.js"};
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
		return new String[] { "fullcalendar.css", "ui.jqgrid.css",
				"datepicker.css", "daterangepicker.css",
				"jquery-ui-1.10.3.custom.min.css", "chosen.css",
				"bootstrap-timepicker.css", "colorpicker.css","bootstrap-datetimepicker.min.css" };
	}
}
