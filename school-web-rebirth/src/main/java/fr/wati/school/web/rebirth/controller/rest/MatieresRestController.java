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

import fr.wati.school.dao.MatiereRepository;
import fr.wati.school.entities.bean.Matiere;
import fr.wati.school.services.MatiereService;
import fr.wati.school.web.rebirth.controller.response.JqgridResponse;
import fr.wati.school.web.rebirth.domain.MatiereDto;
import fr.wati.school.web.rebirth.utils.DtoMapper;
import fr.wati.school.web.rebirth.utils.JqgridFilter;
import fr.wati.school.web.rebirth.utils.JqgridObjectMapper;

@Controller
@RequestMapping(value = "/rest/matieres")
public class MatieresRestController implements RestCrudController<Matiere, MatiereDto>{

	@Autowired
	private MatiereService matiereService;
	@Autowired
	private MatiereRepository matiereRepository;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public @ResponseBody MatiereDto read(@PathVariable("id") int id) {
    return DtoMapper.map(matiereRepository.findOne(Long.valueOf(String.valueOf(id))));
  }

  @RequestMapping(method = RequestMethod.GET)
  public
  @ResponseBody
  JqgridResponse<MatiereDto> records(@RequestParam("_search") Boolean search,
			@RequestParam(value = "filters", required = false) String filters,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {

		Pageable pageRequest = new PageRequest(page - 1, rows);

		if (search == true) {
			return getFilteredRecords(filters, pageRequest);

		}

		Page<Matiere> matieres = matiereRepository.findAll(pageRequest);
		List<MatiereDto> dtos=DtoMapper.mapMatieres(matieres);
		JqgridResponse<MatiereDto> response = new JqgridResponse<MatiereDto>();
		response.setRows(dtos);
		response.setRecords(Long.valueOf(matieres.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(matieres.getTotalPages()).toString());
		response.setPage(Integer.valueOf(matieres.getNumber() + 1).toString());

		return response;
	}

  /**
	 * Helper method for returning filtered records
	 */
	public JqgridResponse<MatiereDto> getFilteredRecords(String filters,
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

		Page<Matiere> matieres = null;
		if (qnom != null)
			matieres = matiereRepository.findByNomLike("%" + qnom + "%",
					pageRequest);
		if (qcode != null)
			matieres = matiereRepository.findByCodeLike("%" + qcode + "%",
					pageRequest);

		List<MatiereDto> matieresDtos = DtoMapper.mapMatieres(matieres);
		JqgridResponse<MatiereDto> response = new JqgridResponse<MatiereDto>();
		response.setRows(matieresDtos);
		response.setRecords(Long.valueOf(matieres.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(matieres.getTotalPages()).toString());
		response.setPage(Integer.valueOf(matieres.getNumber() + 1).toString());
		return response;
	}
  
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable("id") long id, @RequestBody MatiereDto matiereDto) {
	  Matiere matiere = matiereRepository.findOne(id);
	  matiere.setCode(matiereDto.getCode());
	  matiere.setNom(matiereDto.getNom());
	  matiereService.save(matiere);
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<MatiereDto>> list() {
		List<Matiere> matieres = matiereRepository.findAll();
		List<MatiereDto> matiereDtos = DtoMapper
				.mapMatieres(matieres);
		return new ResponseEntity<List<MatiereDto>>(matiereDtos,
				HttpStatus.OK);
	}
  
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<String> create(HttpServletRequest request, @RequestBody MatiereDto matiereDto) {
	  Matiere matiere = new Matiere();
	  matiere.setCode(matiereDto.getCode());
	  matiere.setNom(matiereDto.getNom());
	  matiereService.save(matiere);
	  return new ResponseEntity<String>(matiereDto.getNom()+" created", HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") long id) {
	  matiereService.delete(id);
  }

}