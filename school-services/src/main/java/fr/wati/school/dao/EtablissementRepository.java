package fr.wati.school.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.wati.school.entities.bean.Etablissement;

public interface EtablissementRepository extends
		JpaRepository<Etablissement, Long> {

	Etablissement findByNom(String nom);

	Page<Etablissement> findByNomLike(String string, Pageable pageRequest);
	
	/**
	 * @param string
	 * @param pageRequest
	 * @return
	 */
	Page<Etablissement> findByCodeLike(String string, Pageable pageRequest);

}
