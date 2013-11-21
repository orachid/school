package fr.wati.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.wati.school.entities.bean.Etablissement;

public interface EtablissementRepository extends
		JpaRepository<Etablissement, Long> {

}
