package fr.wati.school.web.rebirth.controller.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import fr.wati.school.dao.EvenementRepository;
import fr.wati.school.entities.bean.Evenement;
import fr.wati.school.entities.bean.EvenementType;
import fr.wati.school.entities.bean.Role;
import fr.wati.school.web.rebirth.controller.response.JqgridResponse;
import fr.wati.school.web.rebirth.domain.CalendarEventDetailDto;
import fr.wati.school.web.rebirth.domain.CalendarEventDto;
import fr.wati.school.web.rebirth.utils.DtoMapper;
import fr.wati.school.web.rebirth.utils.SecurityUtils;

@Controller
@RequestMapping(value = "/rest/calendar")
public class CalendarRestController implements
		RestCrudController<Evenement, CalendarEventDto> {

	@Autowired
	private EvenementRepository evenementRepository;

	public CalendarRestController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<CalendarEventDto>> getEvents(
			@RequestParam(value = "start", required = true) long start,
			@RequestParam(value = "end", required = true) long end) {
		// We receive time in second
		Date startDate = new Date(start * 1000);
		Date endDate = new Date(end * 1000);
		List<Evenement> evenements = evenementRepository
				.findByDateDebutBetween(startDate, endDate);
		List<CalendarEventDto> dtos = DtoMapper.mapEvenement(evenements);
		return new ResponseEntity<List<CalendarEventDto>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkroles", method = RequestMethod.GET)
	public ResponseEntity<Boolean> canCreateEvent(
			@RequestParam(value = "eventType", required = true) String eventType) {
		boolean hasAnyRole = false;
		EvenementType evenementType = EvenementType.valueOf(eventType);
		switch (evenementType) {
		case COURS:
			hasAnyRole = SecurityUtils.hasAnyRole(new String[] {
					Role.ROLE_ADMIN, Role.ROLE_DIRECTOR, Role.ROLE_PROF,
					Role.ROLE_SECRETAIRE });
			break;
		case EVENEMENT:
		case REUNION:
		case CONSEILLE_DE_CLASSE:
			hasAnyRole = SecurityUtils
					.hasAnyRole(new String[] { Role.ROLE_ADMIN,
							Role.ROLE_DIRECTOR, Role.ROLE_SECRETAIRE });
			break;
		default:
			hasAnyRole = false;
			break;
		}
		return new ResponseEntity<Boolean>(hasAnyRole, HttpStatus.OK);
	}

	@RequestMapping(value = "/evenementType", method = RequestMethod.GET)
	public ResponseEntity<List<EvenementType>> getPossibleEvent() {
		List<EvenementType> evenementTypes = new ArrayList<>();
		if (SecurityUtils.hasAnyRole(new String[] { Role.ROLE_ADMIN,
				Role.ROLE_DIRECTOR, Role.ROLE_SECRETAIRE })) {
			evenementTypes.addAll(Arrays.asList(EvenementType.values()));
		}
		if (SecurityUtils.hasAnyRole(new String[] { Role.ROLE_ADMIN,
				Role.ROLE_DIRECTOR, Role.ROLE_SECRETAIRE, Role.ROLE_PROF })) {
			evenementTypes.addAll(Arrays.asList(EvenementType.values()));
		}
		return new ResponseEntity<List<EvenementType>>(evenementTypes,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/eventDetails/{id}", method = RequestMethod.GET)
	public ResponseEntity<CalendarEventDetailDto> getEventDetails(@PathVariable("id") Long id){
		Evenement evenement=evenementRepository.findOne(id);
		if(evenement!=null){
			CalendarEventDetailDto calendarEventDetailDto=DtoMapper.mapForDetails(evenement);
			return new ResponseEntity<CalendarEventDetailDto>(calendarEventDetailDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.wati.school.web.rebirth.controller.rest.RestCrudController#read(int)
	 */
	@Override
	public CalendarEventDto read(int id) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.wati.school.web.rebirth.controller.rest.RestCrudController#records
	 * (java.lang.Boolean, java.lang.String, java.lang.Integer,
	 * java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public JqgridResponse<CalendarEventDto> records(Boolean search,
			String filters, Integer page, Integer rows, String sidx, String sord) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.wati.school.web.rebirth.controller.rest.RestCrudController#
	 * getFilteredRecords(java.lang.String,
	 * org.springframework.data.domain.Pageable)
	 */
	@Override
	public JqgridResponse<CalendarEventDto> getFilteredRecords(String filters,
			Pageable pageRequest) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.wati.school.web.rebirth.controller.rest.RestCrudController#update(
	 * long, java.io.Serializable)
	 */
	@Override
	public void update(long id, CalendarEventDto dto) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.wati.school.web.rebirth.controller.rest.RestCrudController#create(
	 * javax.servlet.http.HttpServletRequest, java.io.Serializable)
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> create(@RequestBody CalendarEventDto calendarEventDto) {
		Evenement evenement = new Evenement();
		evenement.setNom(calendarEventDto.getTitle());
		evenement.setDateDebut(calendarEventDto.getStart());
		evenement.setDateFin(calendarEventDto.getEnd());
		evenementRepository.save(evenement);
		return new ResponseEntity<String>(calendarEventDto.getTitle()
				+ " created", HttpStatus.CREATED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.wati.school.web.rebirth.controller.rest.RestCrudController#delete(
	 * long)
	 */
	@Override
	public void delete(long id) {
	}

}
