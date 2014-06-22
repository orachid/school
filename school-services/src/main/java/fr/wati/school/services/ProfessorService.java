package fr.wati.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.wati.school.dao.ProfessorRepository;
import fr.wati.school.entities.bean.Professeur;

@Transactional
@Service
public class ProfessorService implements CrudService<Professeur, Long> {

	@Autowired
	private ProfessorRepository professorRepository;
	
	public ProfessorService() {
	}

	@Override
	public <S extends Professeur> S save(S entity) {
		return professorRepository.save(entity);
	}

	@Override
	public <S extends Professeur> Iterable<S> save(Iterable<S> entities) {
		return professorRepository.save(entities);
	}

	@Override
	public Professeur findOne(Long id) {
		return professorRepository.findOne(id);
	}

	@Override
	public boolean exists(Long id) {
		return professorRepository.exists(id);
	}

	@Override
	public Iterable<Professeur> findAll() {
		return professorRepository.findAll();
	}

	@Override
	public Iterable<Professeur> findAll(Iterable<Long> ids) {
		return professorRepository.findAll(ids);
	}

	@Override
	public long count() {
		return professorRepository.count();
	}

	@Override
	public void delete(Long id) {
		professorRepository.delete(id);
	}

	@Override
	public void delete(Professeur entity) {
		professorRepository.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends Professeur> entities) {
		professorRepository.delete(entities);
	}

	@Override
	public void deleteAll() {
		professorRepository.deleteAll();
	}

}
