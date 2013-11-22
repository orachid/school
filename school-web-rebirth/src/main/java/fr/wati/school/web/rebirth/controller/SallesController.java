package fr.wati.school.web.rebirth.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.dao.SalleRepository;
import fr.wati.school.entities.bean.Salle;
import fr.wati.school.services.SalleService;
import fr.wati.school.web.rebirth.commons.Breadcrumbs;
import fr.wati.school.web.rebirth.commons.Breadcrumbs.Link;
import fr.wati.school.web.rebirth.commons.Layout;
import fr.wati.school.web.rebirth.controller.response.JqgridResponse;
import fr.wati.school.web.rebirth.controller.response.StatusResponse;
import fr.wati.school.web.rebirth.domain.SalleDto;
import fr.wati.school.web.rebirth.utils.DtoMapper;
import fr.wati.school.web.rebirth.utils.JqgridFilter;
import fr.wati.school.web.rebirth.utils.JqgridObjectMapper;

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
	
	@RequestMapping(value = "/records", produces = "application/json")
	public @ResponseBody
	JqgridResponse<SalleDto> records(@RequestParam("_search") Boolean search,
			@RequestParam(value = "filters", required = false) String filters,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {

		Pageable pageRequest = new PageRequest(page - 1, rows);

		if (search == true) {
			return getFilteredRecords(filters, pageRequest);

		}

		Page<Salle> salles = salleRepository.findAll(pageRequest);
		List<SalleDto> dtos=DtoMapper.mapSalle(salles);
		JqgridResponse<SalleDto> response = new JqgridResponse<SalleDto>();
		response.setRows(dtos);
		response.setRecords(Long.valueOf(salles.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(salles.getTotalPages()).toString());
		response.setPage(Integer.valueOf(salles.getNumber() + 1).toString());

		return response;
	}

	/**
	 * Helper method for returning filtered records
	 */
	public JqgridResponse<SalleDto> getFilteredRecords(String filters,
			Pageable pageRequest) {
		String qnom = null;
		String qcode = null;

		JqgridFilter jqgridFilter = JqgridObjectMapper.map(filters);
		for (JqgridFilter.Rule rule : jqgridFilter.getRules()) {
			if (rule.getField().equals("nom"))
				qnom = rule.getData();
			else if (rule.getField().equals("code"))
				qcode = rule.getData();
		}

		Page<Salle> etablissements = null;
		if (qnom != null)
			etablissements = salleRepository.findByNomLike("%" + qnom + "%",
					pageRequest);
		if (qcode != null)
			etablissements = salleRepository.findByCodeLike("%" + qcode + "%",
					pageRequest);

		List<SalleDto> salleDtos = DtoMapper.mapSalle(etablissements);
		JqgridResponse<SalleDto> response = new JqgridResponse<SalleDto>();
		response.setRows(salleDtos);
		response.setRecords(Long.valueOf(etablissements.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(etablissements.getTotalPages()).toString());
		response.setPage(Integer.valueOf(etablissements.getNumber() + 1).toString());
		return response;
	}

	@RequestMapping(value = "/get", produces = "application/json")
	public @ResponseBody
	Salle get(@RequestBody SalleDto salleDto) {
		return salleRepository.findByNom(salleDto.getNom());
	}

	@RequestMapping(value = "/create", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody
	StatusResponse create(@RequestParam String nom,	@RequestParam String code) {

		// User newUser = new User(username, password, firstName, lastName, new
		// Role(role));
		// Boolean result = service.create(newUser);
		return new StatusResponse(true);
	}

	@RequestMapping(value = "/update", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody
	StatusResponse update(@RequestParam String nom,	@RequestParam String code) {

		// Users existingUser = new Users();
		//
		// Boolean result = userService. update(existingUser);
		return new StatusResponse(true);
	}

	@RequestMapping(value = "/delete", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody
	StatusResponse delete(@RequestParam Long id) {

		// User existingUser = new User(username);
		// Boolean result = service.delete(existingUser);
		return new StatusResponse(true);
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
