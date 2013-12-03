package fr.wati.school.web.rebirth.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import fr.wati.school.entities.bean.Classe;
import fr.wati.school.entities.bean.Cours;
import fr.wati.school.entities.bean.Etablissement;
import fr.wati.school.entities.bean.Evenement;
import fr.wati.school.entities.bean.Matiere;
import fr.wati.school.entities.bean.Personne;
import fr.wati.school.entities.bean.Salle;
import fr.wati.school.entities.bean.Users;
import fr.wati.school.web.rebirth.domain.CalendarEventDetailDto;
import fr.wati.school.web.rebirth.domain.CalendarEventDto;
import fr.wati.school.web.rebirth.domain.ClasseDto;
import fr.wati.school.web.rebirth.domain.EtablissementDto;
import fr.wati.school.web.rebirth.domain.MatiereDto;
import fr.wati.school.web.rebirth.domain.PersonneDto;
import fr.wati.school.web.rebirth.domain.SalleDto;
import fr.wati.school.web.rebirth.domain.UserDto;

public class DtoMapper {

	public static UserDto map(Users user) {
		UserDto dto = new UserDto();
		dto.setId(Integer.valueOf(user.getId().toString()));
		dto.setUsername(user.getUsername());
		return dto;
	}

	public static List<UserDto> mapUsers(Page<Users> users) {
		List<UserDto> dtos = new ArrayList<UserDto>();
		for (Users user : users) {
			dtos.add(map(user));
		}
		return dtos;
	}

	public static EtablissementDto map(Etablissement etablissement) {
		EtablissementDto dto = new EtablissementDto();
		dto.setId(Long.valueOf(etablissement.getId().toString()));
		dto.setNom(etablissement.getNom());
		dto.setCode(etablissement.getCode());
		return dto;
	}

	public static List<EtablissementDto> mapEtablissement(
			Page<Etablissement> etablissements) {
		List<EtablissementDto> dtos = new ArrayList<EtablissementDto>();
		for (Etablissement etablissement : etablissements) {
			dtos.add(map(etablissement));
		}
		return dtos;
	}

	public static List<EtablissementDto> mapEtablissement(
			List<Etablissement> etablissements) {
		List<EtablissementDto> dtos = new ArrayList<EtablissementDto>();
		for (Etablissement etablissement : etablissements) {
			dtos.add(map(etablissement));
		}
		return dtos;
	}

	public static SalleDto map(Salle salle) {
		SalleDto dto = new SalleDto();
		dto.setId(Long.valueOf(salle.getId().toString()));
		dto.setNom(salle.getNom());
		dto.setCode(salle.getCode());
		return dto;
	}

	public static List<SalleDto> mapSalles(Iterable<Salle> salles) {
		List<SalleDto> dtos = new ArrayList<SalleDto>();
		for (Salle salle : salles) {
			dtos.add(map(salle));
		}
		return dtos;
	}

	public static MatiereDto map(Matiere matiere) {
		MatiereDto dto = new MatiereDto();
		dto.setId(Long.valueOf(matiere.getId().toString()));
		dto.setNom(matiere.getNom());
		dto.setCode(matiere.getCode());
		return dto;
	}

	public static List<MatiereDto> mapMatieres(Iterable<Matiere> matieres) {
		List<MatiereDto> dtos = new ArrayList<MatiereDto>();
		for (Matiere matiere : matieres) {
			dtos.add(map(matiere));
		}
		return dtos;
	}

	public static PersonneDto map(Personne personne) {
		PersonneDto dto = new PersonneDto();
		dto.setId(Long.valueOf(personne.getId().toString()));
		dto.setUsername(personne.getUsername());
		dto.setNom(personne.getNom());
		dto.setPrenom(personne.getPrenom());
		dto.setCivilite(personne.getCivilite());
		dto.setCodePostal(personne.getContact().getAdresse().getCodePostal());
		dto.setEmail(personne.getContact().getEmail());
		dto.setRue(personne.getContact().getAdresse().getRue());
		dto.setDateNaissance(personne.getDateNaissance());
		dto.setNumeroTelephone(personne.getContact().getNumeroTelephone());
		return dto;
	}

	public static List<PersonneDto> mapPersonne(Page<Personne> personnes) {
		List<PersonneDto> dtos = new ArrayList<PersonneDto>();
		for (Personne personne : personnes) {
			dtos.add(map(personne));
		}
		return dtos;
	}

	public static ClasseDto map(Classe classe) {
		ClasseDto dto = new ClasseDto();
		dto.setId(Long.valueOf(classe.getId().toString()));
		dto.setNom(classe.getNom());
		dto.setCode(classe.getCode());
		return dto;
	}

	public static List<ClasseDto> mapClasse(Page<Classe> classes) {
		List<ClasseDto> dtos = new ArrayList<ClasseDto>();
		for (Classe classe : classes) {
			dtos.add(map(classe));
		}
		return dtos;
	}
	
	public static List<ClasseDto> mapClasses(List<Classe> classes) {
		List<ClasseDto> dtos = new ArrayList<ClasseDto>();
		for (Classe classe : classes) {
			dtos.add(map(classe));
		}
		return dtos;
	}

	public static CalendarEventDto map(Evenement evenement) {
		CalendarEventDto dto = new CalendarEventDto();
		dto.setId(Long.valueOf(evenement.getId().toString()));
		dto.setTitle(evenement.getNom());
		dto.setAllDay(false);
		dto.setStart(evenement.getDateDebut());
		dto.setEnd(evenement.getDateFin());
		//dto.setEditable(true);
		return dto;
	}
	
	public static CalendarEventDetailDto mapForDetails(Evenement evenement) {
		CalendarEventDetailDto dto = new CalendarEventDetailDto();
		dto.setId(Long.valueOf(evenement.getId().toString()));
		dto.setTitle(evenement.getNom());
		dto.setAllDay(false);
		dto.setStart(evenement.getDateDebut());
		dto.setEnd(evenement.getDateFin());
		if(evenement instanceof Cours){
			dto.setSalle(((Cours)evenement).getSalle().getCode());
			dto.setMatiere(((Cours)evenement).getMatiere().getCode());
			dto.setProfesseur(((Cours)evenement).getProfesseur().getFullName());
			dto.setClasse(((Cours)evenement).getClasse().getCode());
		}
		
		return dto;
	}

	public static List<CalendarEventDto> mapEvenement(List<Evenement> evenements) {
		List<CalendarEventDto> dtos = new ArrayList<CalendarEventDto>();
		for (Evenement evenement : evenements) {
			dtos.add(map(evenement));
		}
		return dtos;
	}
}
