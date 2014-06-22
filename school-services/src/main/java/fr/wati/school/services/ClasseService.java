package fr.wati.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.wati.school.dao.ClasseRepository;
import fr.wati.school.entities.bean.Classe;

@Transactional
@Service
public class ClasseService implements CrudService<Classe, Long> {

	@Autowired
	private ClasseRepository classeRepository;
	
	public ClasseService() {
	}

	@Override
	public <S extends Classe> S save(S entity) {
		return classeRepository.save(entity);
	}

	@Override
	public <S extends Classe> Iterable<S> save(Iterable<S> entities) {
		return classeRepository.save(entities);
	}

	@Override
	public Classe findOne(Long id) {
		return classeRepository.findOne(id);
	}

	@Override
	public boolean exists(Long id) {
		return classeRepository.exists(id);
	}

	@Override
	public Iterable<Classe> findAll() {
		return classeRepository.findAll();
	}

	@Override
	public Iterable<Classe> findAll(Iterable<Long> ids) {
		return classeRepository.findAll(ids);
	}

	@Override
	public long count() {
		return classeRepository.count();
	}

	@Override
	public void delete(Long id) {
		classeRepository.delete(id);
	}

	@Override
	public void delete(Classe entity) {
		classeRepository.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends Classe> entities) {
		classeRepository.delete(entities);
	}

	@Override
	public void deleteAll() {
		classeRepository.deleteAll();
	}

}
