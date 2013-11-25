package fr.wati.school.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.wati.school.entities.bean.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {

	List<Evenement> findByDateDebutBetween(Date startDate,Date endDate);
	
}
