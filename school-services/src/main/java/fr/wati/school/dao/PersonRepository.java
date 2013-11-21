package fr.wati.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.wati.school.entities.bean.Personne;

public interface PersonRepository extends JpaRepository<Personne, Long> {

	Personne findByUsername(String username);
}
