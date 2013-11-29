package fr.wati.school.web.rebirth.controller.rest;

import java.util.List;

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

import fr.wati.school.dao.SalleRepository;
import fr.wati.school.entities.bean.Salle;
import fr.wati.school.services.SalleService;
import fr.wati.school.web.rebirth.controller.response.JqgridResponse;
import fr.wati.school.web.rebirth.domain.SalleDto;
import fr.wati.school.web.rebirth.utils.DtoMapper;
import fr.wati.school.web.rebirth.utils.JqgridFilter;
import fr.wati.school.web.rebirth.utils.JqgridObjectMapper;

@Controller
@RequestMapping(value = "/rest/salles")
public class SallesRestController implements RestCrudController<Salle,SalleDto> {

	@Autowired
	private SalleService salleService;
	@Autowired
	private SalleRepository salleRepository;

  /* (non-Javadoc)
 * @see fr.wati.school.web.rebirth.controller.rest.RestController#read(int)
 */
@Override
@RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public @ResponseBody SalleDto read(@PathVariable("id") int id) {
    return DtoMapper.map(salleRepository.findOne(Long.valueOf(String.valueOf(id))));
  }

  /* (non-Javadoc)
 * @see fr.wati.school.web.rebirth.controller.rest.RestController#records(java.lang.Boolean, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
 */
@Override
@RequestMapping(method = RequestMethod.GET)
  public
  @ResponseBody
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
		List<SalleDto> dtos=DtoMapper.mapSalles(salles);
		JqgridResponse<SalleDto> response = new JqgridResponse<SalleDto>();
		response.setRows(dtos);
		response.setRecords(Long.valueOf(salles.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(salles.getTotalPages()).toString());
		response.setPage(Integer.valueOf(salles.getNumber() + 1).toString());

		return response;
	}

  /* (non-Javadoc)
 * @see fr.wati.school.web.rebirth.controller.rest.RestController#getFilteredRecords(java.lang.String, org.springframework.data.domain.Pageable)
 */
	@Override
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

		Page<Salle> salles = null;
		if (qnom != null)
			salles = salleRepository.findByNomLike("%" + qnom + "%",
					pageRequest);
		if (qcode != null)
			salles = salleRepository.findByCodeLike("%" + qcode + "%",
					pageRequest);

		List<SalleDto> sallesDtos = DtoMapper.mapSalles(salles);
		JqgridResponse<SalleDto> response = new JqgridResponse<SalleDto>();
		response.setRows(sallesDtos);
		response.setRecords(Long.valueOf(salles.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(salles.getTotalPages()).toString());
		response.setPage(Integer.valueOf(salles.getNumber() + 1).toString());
		return response;
	}
  
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<SalleDto>> list() {
		List<Salle> salles = salleRepository.findAll();
		List<SalleDto> sallesDtos = DtoMapper
				.mapSalles(salles);
		return new ResponseEntity<List<SalleDto>>(sallesDtos,
				HttpStatus.OK);
	}
	
  /* (non-Javadoc)
 * @see fr.wati.school.web.rebirth.controller.rest.RestController#updateBook(long, fr.wati.school.web.rebirth.domain.SalleDto)
 */
@Override
@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable("id") long id, @RequestBody SalleDto salleDto) {
	  Salle salle = salleRepository.findOne(id);
	  salle.setCode(salleDto.getCode());
	  salle.setNom(salleDto.getNom());
	  salleService.save(salle);
  }

  /* (non-Javadoc)
 * @see fr.wati.school.web.rebirth.controller.rest.RestController#createBook(javax.servlet.http.HttpServletRequest, fr.wati.school.web.rebirth.domain.SalleDto)
 */
@Override
@RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<String> create(@RequestBody SalleDto salleDto) {
	  Salle salle = new Salle();
	  salle.setCode(salleDto.getCode());
	  salle.setNom(salleDto.getNom());
	  salleService.save(salle);
	  return new ResponseEntity<String>(salleDto.getNom()+" created", HttpStatus.CREATED);
  }

  /* (non-Javadoc)
 * @see fr.wati.school.web.rebirth.controller.rest.RestController#deleteBook(long)
 */
@Override
@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") long id) {
	  salleService.delete(id);
  }

}