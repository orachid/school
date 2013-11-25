package fr.wati.school.web.rebirth.controller.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.wati.school.dao.EvenementRepository;
import fr.wati.school.entities.bean.Evenement;
import fr.wati.school.entities.bean.EvenementType;
import fr.wati.school.entities.bean.Role;
import fr.wati.school.web.rebirth.domain.CalendarEventDto;
import fr.wati.school.web.rebirth.utils.DtoMapper;
import fr.wati.school.web.rebirth.utils.SecurityUtils;

@Controller
@RequestMapping(value = "/rest/calendar")
public class CalendarRestController {

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
		EvenementType evenementType=EvenementType.valueOf(eventType);
		switch (evenementType) {
		case COURS:
			hasAnyRole = SecurityUtils.hasAnyRole(new String[]{Role.ROLE_ADMIN,Role.ROLE_DIRECTOR,Role.ROLE_PROF,Role.ROLE_SECRETAIRE});
			break;
		case EVENEMENT:
		case REUNION:
		case CONSEILLE_DE_CLASSE:
			hasAnyRole = SecurityUtils.hasAnyRole(new String[]{Role.ROLE_ADMIN,Role.ROLE_DIRECTOR,Role.ROLE_SECRETAIRE});
			break;
		default:
			hasAnyRole=false;
			break;
		}
		return new ResponseEntity<Boolean>(hasAnyRole, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/evenementType", method = RequestMethod.GET)
	public ResponseEntity<List<EvenementType>> getPossibleEvent(){
		List<EvenementType> evenementTypes=new ArrayList<>();
		if(SecurityUtils.hasAnyRole(new String[]{Role.ROLE_ADMIN,Role.ROLE_DIRECTOR,Role.ROLE_SECRETAIRE})){
			evenementTypes.addAll(Arrays.asList(EvenementType.values()));
		}
		if(SecurityUtils.hasAnyRole(new String[]{Role.ROLE_ADMIN,Role.ROLE_DIRECTOR,Role.ROLE_SECRETAIRE,Role.ROLE_PROF})){
			evenementTypes.addAll(Arrays.asList(EvenementType.values()));
		}
		return new ResponseEntity<List<EvenementType>>(evenementTypes, HttpStatus.OK);
	} 
}
