package fr.wati.school.web.rebirth.controller.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.dao.UsersRepository;
import fr.wati.school.services.UserService;
import fr.wati.school.web.rebirth.commons.Breadcrumbs;
import fr.wati.school.web.rebirth.commons.Breadcrumbs.Link;
import fr.wati.school.web.rebirth.commons.Layout;
import fr.wati.school.web.rebirth.utils.ImprovedMustacheTemplateLoader;

@Controller
@RequestMapping("/users")
public class UsersController extends AbstractDefaultPageController {

	@Autowired
	private UserService userService;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private ImprovedMustacheTemplateLoader improvedMustacheTemplateLoader;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView render() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = super.render(modelAndView);	
		//SideNav
		Layout layout=Layout.buildLayoutDefault();
		Layout.active("/users");
		modelAndView.addObject("layout",layout );
		return modelAndView;
	}
	
	@Override
	public String getTitle() {
		return "Users edition";
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.view.AbstractDefaultPageController#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Create update delete users";
	}
	
	@Override
	public String getInline_scripts() {
		return "views/assets/scripts/personnes-jqgrid.js";
	}

	@Override
	public String getContent() {
		return "/pages/crud/crud-edition";
	}

	@Override
	public Breadcrumbs getBreadcrumbs() {
		List<Link> links=new ArrayList<>();
		links.add(new Link("#", "Admin"));
		links.add(new Link("#", "Edition"));
		Breadcrumbs breadcrumbs=new Breadcrumbs("User edition", links);
		return breadcrumbs;
	}

	@Override
	public String[] getScripts() {
		return new String[]{"jqGrid/jquery.jqGrid.min.js","jqGrid/i18n/grid.locale-en.js","date-time/bootstrap-datepicker.min.js"};
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.AbstractDefaultPageController#getStyles()
	 */
	@Override
	public String[] getStyles() {
		return new String[]{"jquery-ui.min.css","ui.jqgrid.css","datepicker.css"};
	}

	
	
	
}
