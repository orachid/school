package fr.wati.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.wati.school.entities.bean.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

}
