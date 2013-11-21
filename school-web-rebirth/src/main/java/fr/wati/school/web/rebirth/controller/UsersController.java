package fr.wati.school.web.rebirth.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.dao.UsersRepository;
import fr.wati.school.entities.bean.Users;
import fr.wati.school.services.UserService;
import fr.wati.school.web.rebirth.commons.Breadcrumbs;
import fr.wati.school.web.rebirth.commons.Breadcrumbs.Link;
import fr.wati.school.web.rebirth.commons.Layout;
import fr.wati.school.web.rebirth.commons.navigation.SideNavItem;
import fr.wati.school.web.rebirth.commons.navigation.SideNavItemsFactory;
import fr.wati.school.web.rebirth.controller.response.JqgridResponse;
import fr.wati.school.web.rebirth.controller.response.StatusResponse;
import fr.wati.school.web.rebirth.domain.UserDto;
import fr.wati.school.web.rebirth.utils.ImprovedMustacheTemplateLoader;
import fr.wati.school.web.rebirth.utils.JqgridFilter;
import fr.wati.school.web.rebirth.utils.JqgridObjectMapper;
import fr.wati.school.web.rebirth.utils.SecurityUtils;
import fr.wati.school.web.rebirth.utils.UserMapper;

@Controller
@RequestMapping("/users")
public class UsersController extends AbstractDefaultPageController {

	@Autowired
	private UserService userService;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private SideNavItemsFactory sideNavItemsFactory;
	@Autowired
	private ImprovedMustacheTemplateLoader improvedMustacheTemplateLoader;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView render() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = super.render(modelAndView);
//		improvedMustacheTemplateLoader.getIncludedTemplatePath().get()
//				.put("page.content", "/pages/crud/crud-edition");
		//modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("recordsUrl", "/users/records");
		modelAndView.addObject("addUrl", "/users/create");
		modelAndView.addObject("editUrl", "/users/update");
		modelAndView.addObject("deleteUrl", "/users/delete");
		//SideNav
		Layout layout=new Layout();
		SideNavItem admin=(SideNavItem) SecurityUtils.applicationContext.getBean("admin");
		admin.setClazz("open");
		SideNavItem adminEdition=(SideNavItem) SecurityUtils.applicationContext.getBean("adminEdition");
		adminEdition.setClazz("open");
		SideNavItem userEdition=(SideNavItem) SecurityUtils.applicationContext.getBean("usersEdition");
		userEdition.setClazz("active");
		layout.setSidenav_navList(sideNavItemsFactory.getSideNavItems().toArray(new SideNavItem[sideNavItemsFactory.getSideNavItems().size()]));
		modelAndView.addObject("layout", layout);
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUsers(@ModelAttribute("users") Users users,
			BindingResult result) {

		if (users != null) {
			userService.save(users);
		}

		return "redirect:show-users";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable Long id) {
		if (id != null && userService.exists(id)) {
			userService.delete(id);
		}
		return "redirect:/users";
	}

	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)  
    public ModelAndView editUser(@PathVariable Long id) {  
        ModelAndView modelAndView=new ModelAndView();
        modelAndView= super.render(modelAndView);
        modelAndView.setViewName("/layouts/default");
        super.processModelAndView(modelAndView);
        improvedMustacheTemplateLoader.getIncludedTemplatePath().get().put("page.content","/pages/users/edit-user");
        Users users=userService.findOne(id);  
        modelAndView.addObject("user",users);  
        return modelAndView;  
    }  
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("users") Users users,@PathVariable Long id,
			BindingResult result) {
		if (id != null && userService.exists(id)) {
			Users findUser = userService.findOne(id);
			findUser.setEnabled(users.isEnabled());
			findUser.setUsername(users.getUsername());
			findUser.setPassword(users.getPassword());
			userService.save(findUser);
		}
		return "redirect:/users";
	}

	
	@RequestMapping(value="/records", produces="application/json")
	public @ResponseBody JqgridResponse<UserDto> records(
    		@RequestParam("_search") Boolean search,
    		@RequestParam(value="filters", required=false) String filters,
    		@RequestParam(value="page", required=false) Integer page,
    		@RequestParam(value="rows", required=false) Integer rows,
    		@RequestParam(value="sidx", required=false) String sidx,
    		@RequestParam(value="sord", required=false) String sord) {

		Pageable pageRequest = new PageRequest(page-1, rows);
		
		if (search == true) {
			return getFilteredRecords(filters, pageRequest);
			
		} 
			
		Page<Users> users = usersRepository.findAll(pageRequest);
		List<UserDto> userDtos = UserMapper.map(users);
		
		JqgridResponse<UserDto> response = new JqgridResponse<UserDto>();
		response.setRows(userDtos);
		response.setRecords(Long.valueOf(users.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(users.getTotalPages()).toString());
		response.setPage(Integer.valueOf(users.getNumber()+1).toString());
		
		return response;
	}
	
	/**
	 * Helper method for returning filtered records
	 */
	public JqgridResponse<UserDto> getFilteredRecords(String filters, Pageable pageRequest) {
		String qUsername = null;
		String qPassword = null;
		
		
		JqgridFilter jqgridFilter = JqgridObjectMapper.map(filters);
		for (JqgridFilter.Rule rule: jqgridFilter.getRules()) {
			if (rule.getField().equals("username"))
				qUsername = rule.getData();
			else if (rule.getField().equals("password"))
				qPassword = rule.getData();
		}
		
		Page<Users> users = null;
		if (qUsername != null) 
			users = usersRepository.findByUsernameLike("%"+qUsername+"%", pageRequest);
		
		List<UserDto> userDtos = UserMapper.map(users);
		JqgridResponse<UserDto> response = new JqgridResponse<UserDto>();
		response.setRows(userDtos);
		response.setRecords(Long.valueOf(users.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(users.getTotalPages()).toString());
		response.setPage(Integer.valueOf(users.getNumber()+1).toString());
		return response;
	}
	
	
	@RequestMapping(value="/get", produces="application/json")
	public @ResponseBody UserDto get(@RequestBody UserDto user) {
		return UserMapper.map(usersRepository.findByUsername(user.getUsername()));
	}

	@RequestMapping(value="/create", produces="application/json", method=RequestMethod.POST)
	public @ResponseBody StatusResponse create(
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String firstName,
			@RequestParam String lastName,
			@RequestParam Integer role) {
		
//		User newUser = new User(username, password, firstName, lastName, new Role(role));
//		Boolean result = service.create(newUser);
		return new StatusResponse(true);
	}
	
	@RequestMapping(value="/update", produces="application/json", method=RequestMethod.POST)
	public @ResponseBody StatusResponse update(
			@RequestParam String username,
			@RequestParam String firstName,
			@RequestParam String lastName,
			@RequestParam Integer role) {
		
//		Users existingUser = new Users();
//		
//		Boolean result = userService. update(existingUser);
		return new StatusResponse(true);
	}
	
	@RequestMapping(value="/delete", produces="application/json", method=RequestMethod.POST)
	public @ResponseBody StatusResponse delete(
			@RequestParam String username) {

//		User existingUser = new User(username);
//		Boolean result = service.delete(existingUser);
		return new StatusResponse(true);
	}
	
	@Override
	public String getInline_scripts() {
		return "views/assets/scripts/users-jqgrid.js";
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
		return new String[]{"jqGrid/jquery.jqGrid.min.js","jqGrid/i18n/grid.locale-en.js"};
	}

	/* (non-Javadoc)
	 * @see fr.wati.school.web.rebirth.controller.AbstractDefaultPageController#getStyles()
	 */
	@Override
	public String[] getStyles() {
		return new String[]{"jquery-ui-1.10.3.full.min.css","ui.jqgrid.css","datepicker.css"};
	}

	
	
	
}
