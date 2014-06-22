package fr.wati.school.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.wati.school.entities.bean.Classe;

public interface ClasseRepository extends JpaRepository<Classe, Long> {

	Page<Classe> findByNomLike(String string, Pageable pageRequest);

	Page<Classe> findByCodeLike(String string, Pageable pageRequest);

}
