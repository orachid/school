package fr.wati.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.wati.school.entities.bean.Professeur;

public interface ProfessorRepository extends JpaRepository<Professeur, Long> {

}
