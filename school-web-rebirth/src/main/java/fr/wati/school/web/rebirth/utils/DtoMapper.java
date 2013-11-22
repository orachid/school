package fr.wati.school.web.rebirth.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import fr.wati.school.entities.bean.Etablissement;
import fr.wati.school.entities.bean.Matiere;
import fr.wati.school.entities.bean.Salle;
import fr.wati.school.entities.bean.Users;
import fr.wati.school.web.rebirth.domain.EtablissementDto;
import fr.wati.school.web.rebirth.domain.MatiereDto;
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
	
	public static SalleDto map(Salle salle) {
		SalleDto dto = new SalleDto();
		dto.setId(Long.valueOf(salle.getId().toString()));
		dto.setNom(salle.getNom());
		dto.setCode(salle.getCode());
		return dto;
	}

	public static List<SalleDto> mapSalle(
			Page<Salle> salles) {
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

	public static List<MatiereDto> mapMatiere(
			Page<Matiere> matieres) {
		List<MatiereDto> dtos = new ArrayList<MatiereDto>();
		for (Matiere matiere : matieres) {
			dtos.add(map(matiere));
		}
		return dtos;
	}
}
