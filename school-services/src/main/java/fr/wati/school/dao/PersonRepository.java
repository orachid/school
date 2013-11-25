package fr.wati.school.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.wati.school.entities.bean.Personne;

public interface PersonRepository extends JpaRepository<Personne, Long> {

	Personne findByUsername(String username);

	Page<Personne> findByNomLike(String string, Pageable pageRequest);

	Page<Personne> findByPrenomLike(String string, Pageable pageRequest);
}
