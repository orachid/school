package fr.wati.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.wati.school.entities.bean.Classe;

public interface ClasseRepository extends JpaRepository<Classe, Long> {

}
