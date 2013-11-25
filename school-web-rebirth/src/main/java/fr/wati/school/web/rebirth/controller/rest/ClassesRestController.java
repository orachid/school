package fr.wati.school.web.rebirth.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.wati.school.dao.ClasseRepository;
import fr.wati.school.entities.bean.Classe;
import fr.wati.school.entities.bean.Etablissement;
import fr.wati.school.services.ClasseService;
import fr.wati.school.web.rebirth.controller.response.JqgridResponse;
import fr.wati.school.web.rebirth.domain.ClasseDto;
import fr.wati.school.web.rebirth.domain.EtablissementDto;
import fr.wati.school.web.rebirth.utils.DtoMapper;
import fr.wati.school.web.rebirth.utils.JqgridFilter;
import fr.wati.school.web.rebirth.utils.JqgridObjectMapper;

@Controller
@RequestMapping(value = "/rest/classes")
public class ClassesRestController implements
		RestCrudController<Classe, ClasseDto> {

	@Autowired
	private ClasseService classeService;
	@Autowired
	private ClasseRepository classeRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.wati.school.web.rebirth.controller.rest.RestController#read(int)
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	ClasseDto read(@PathVariable("id") int id) {
		return DtoMapper.map(classeRepository.findOne(Long.valueOf(String
				.valueOf(id))));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.wati.school.web.rebirth.controller.rest.RestController#records(java
	 * .lang.Boolean, java.lang.String, java.lang.Integer, java.lang.Integer,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	JqgridResponse<ClasseDto> records(@RequestParam("_search") Boolean search,
			@RequestParam(value = "filters", required = false) String filters,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {

		Pageable pageRequest = new PageRequest(page - 1, rows);

		if (search == true) {
			return getFilteredRecords(filters, pageRequest);

		}

		Page<Classe> classes = classeRepository.findAll(pageRequest);
		List<ClasseDto> dtos = DtoMapper.mapClasse(classes);
		JqgridResponse<ClasseDto> response = new JqgridResponse<ClasseDto>();
		response.setRows(dtos);
		response.setRecords(Long.valueOf(classes.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(classes.getTotalPages()).toString());
		response.setPage(Integer.valueOf(classes.getNumber() + 1).toString());

		return response;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<ClasseDto>> list() {
		List<Classe> classes = classeRepository.findAll();
		List<ClasseDto> classeDtos = DtoMapper
				.mapClasses(classes);
		return new ResponseEntity<List<ClasseDto>>(classeDtos,
				HttpStatus.OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.wati.school.web.rebirth.controller.rest.RestController#getFilteredRecords
	 * (java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	public JqgridResponse<ClasseDto> getFilteredRecords(String filters,
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

		Page<Classe> classes = null;
		if (qnom != null)
			classes = classeRepository.findByNomLike("%" + qnom + "%",
					pageRequest);
		if (qcode != null)
			classes = classeRepository.findByCodeLike("%" + qcode + "%",
					pageRequest);

		List<ClasseDto> classesDtos = DtoMapper.mapClasse(classes);
		JqgridResponse<ClasseDto> response = new JqgridResponse<ClasseDto>();
		response.setRows(classesDtos);
		response.setRecords(Long.valueOf(classes.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(classes.getTotalPages()).toString());
		response.setPage(Integer.valueOf(classes.getNumber() + 1).toString());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.wati.school.web.rebirth.controller.rest.RestController#updateBook(
	 * long, fr.wati.school.web.rebirth.domain.SalleDto)
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") long id,
			@RequestBody ClasseDto classeDto) {
		Classe classe = classeRepository.findOne(id);
		classe.setCode(classeDto.getCode());
		classe.setNom(classeDto.getNom());
		classeService.save(classe);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.wati.school.web.rebirth.controller.rest.RestController#createBook(
	 * javax.servlet.http.HttpServletRequest,
	 * fr.wati.school.web.rebirth.domain.SalleDto)
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> create(HttpServletRequest request,
			@RequestBody ClasseDto classeDto) {
		Classe classe = new Classe();
		classe.setCode(classeDto.getCode());
		classe.setNom(classeDto.getNom());
		classeService.save(classe);
		return new ResponseEntity<String>(classeDto.getNom() + " created",
				HttpStatus.CREATED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.wati.school.web.rebirth.controller.rest.RestController#deleteBook(
	 * long)
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") long id) {
		classeService.delete(id);
	}

}