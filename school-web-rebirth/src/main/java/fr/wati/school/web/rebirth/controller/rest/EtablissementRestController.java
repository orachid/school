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

import fr.wati.school.dao.EtablissementRepository;
import fr.wati.school.entities.bean.Etablissement;
import fr.wati.school.services.EtablissementService;
import fr.wati.school.web.rebirth.controller.response.JqgridResponse;
import fr.wati.school.web.rebirth.domain.EtablissementDto;
import fr.wati.school.web.rebirth.utils.DtoMapper;
import fr.wati.school.web.rebirth.utils.JqgridFilter;
import fr.wati.school.web.rebirth.utils.JqgridObjectMapper;

@Controller
@RequestMapping(value = "/rest/etablissements")
public class EtablissementRestController implements
		RestCrudController<Etablissement, EtablissementDto> {

	@Autowired
	private EtablissementService etablissementService;
	@Autowired
	private EtablissementRepository etablissementRepository;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	EtablissementDto read(@PathVariable("id") int id) {
		return DtoMapper.map(etablissementRepository.findOne(Long
				.valueOf(String.valueOf(id))));
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<EtablissementDto>> list() {
		List<Etablissement> etablissement = etablissementRepository.findAll();
		List<EtablissementDto> etablissementDtos = DtoMapper.mapEtablissement(etablissement);
		return new ResponseEntity<List<EtablissementDto>>(etablissementDtos,
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	JqgridResponse<EtablissementDto> records(
			@RequestParam(value="_search", required=false) Boolean search,
			@RequestParam(value = "filters", required = false) String filters,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {

		Pageable pageRequest = new PageRequest(page - 1, rows);
		if(search==null) search=false;
		if (search == true) {
			return getFilteredRecords(filters, pageRequest);

		}

		Page<Etablissement> etablissement = etablissementRepository
				.findAll(pageRequest);
		List<EtablissementDto> dtos = DtoMapper.mapEtablissement(etablissement);
		JqgridResponse<EtablissementDto> response = new JqgridResponse<EtablissementDto>();
		response.setRows(dtos);
		response.setRecords(Long.valueOf(etablissement.getTotalElements())
				.toString());
		response.setTotal(Integer.valueOf(etablissement.getTotalPages())
				.toString());
		response.setPage(Integer.valueOf(etablissement.getNumber() + 1)
				.toString());

		return response;
	}

	/**
	 * Helper method for returning filtered records
	 */
	public JqgridResponse<EtablissementDto> getFilteredRecords(String filters,
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

		Page<Etablissement> etablissements = null;
		if (qnom != null)
			etablissements = etablissementRepository.findByNomLike("%" + qnom
					+ "%", pageRequest);
		if (qcode != null)
			etablissements = etablissementRepository.findByCodeLike("%" + qcode
					+ "%", pageRequest);

		List<EtablissementDto> matieresDtos = DtoMapper
				.mapEtablissement(etablissements);
		JqgridResponse<EtablissementDto> response = new JqgridResponse<EtablissementDto>();
		response.setRows(matieresDtos);
		response.setRecords(Long.valueOf(etablissements.getTotalElements())
				.toString());
		response.setTotal(Integer.valueOf(etablissements.getTotalPages())
				.toString());
		response.setPage(Integer.valueOf(etablissements.getNumber() + 1)
				.toString());
		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") long id,
			@RequestBody EtablissementDto etablissementDto) {
		Etablissement etablissement = etablissementRepository.findOne(id);
		etablissement.setCode(etablissementDto.getCode());
		etablissement.setNom(etablissementDto.getNom());
		etablissementService.save(etablissement);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> create(@RequestBody EtablissementDto etablissementDto) {
		Etablissement etablissement = new Etablissement();
		etablissement.setCode(etablissementDto.getCode());
		etablissement.setNom(etablissementDto.getNom());
		etablissementService.save(etablissement);
		return new ResponseEntity<String>(etablissementDto.getNom()
				+ " created", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") long id) {
		etablissementService.delete(id);
	}

	@Override
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public @ResponseBody List<EtablissementDto> getAll() {
		Pageable pageRequest = new PageRequest(0, 100);
		return DtoMapper.mapEtablissement(etablissementRepository.findAll(pageRequest));
	}
}