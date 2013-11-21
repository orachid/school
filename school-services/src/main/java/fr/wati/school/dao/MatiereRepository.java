package fr.wati.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.wati.school.entities.bean.Matiere;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {

}
