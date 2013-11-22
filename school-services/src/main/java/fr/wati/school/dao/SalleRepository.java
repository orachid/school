package fr.wati.school.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.wati.school.entities.bean.Salle;

public interface SalleRepository extends JpaRepository<Salle, Long> {

	/**
	 * @param string
	 * @param pageRequest
	 * @return
	 */
	Page<Salle> findByNomLike(String string, Pageable pageRequest);

	/**
	 * @param nom
	 * @return
	 */
	Salle findByNom(String nom);
	
	/**
	 * @param string
	 * @param pageRequest
	 * @return
	 */
	Page<Salle> findByCodeLike(String string, Pageable pageRequest);

}
