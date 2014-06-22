package fr.wati.school.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.wati.school.entities.bean.Matiere;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {

	/**
	 * @param string
	 * @param pageRequest
	 * @return
	 */
	Page<Matiere> findByNomLike(String string, Pageable pageRequest);

	/**
	 * @param nom
	 * @return
	 */
	Matiere findByNom(String nom);

	/**
	 * @param string
	 * @param pageRequest
	 * @return
	 */
	Page<Matiere> findByCodeLike(String string, Pageable pageRequest);

}
