package fr.wati.school.web.rebirth.domain;


@SuppressWarnings("serial")
public class CalendarEventDetailDto extends CalendarEventDto {

	
	private String professeur;
	
	private String matiere;
	
	private String classe;
	
	private String salle;
	
	public CalendarEventDetailDto() {
	}

	public String getProfesseur() {
		return professeur;
	}

	public void setProfesseur(String professeur) {
		this.professeur = professeur;
	}

	public String getMatiere() {
		return matiere;
	}

	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getSalle() {
		return salle;
	}

	public void setSalle(String salle) {
		this.salle = salle;
	}

	
	
}
