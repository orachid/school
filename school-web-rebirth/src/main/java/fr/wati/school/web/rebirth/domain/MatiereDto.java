package fr.wati.school.web.rebirth.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MatiereDto implements Serializable {

	private Long id;
	private String nom;
	private String code;
	
	public MatiereDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
