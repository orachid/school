package fr.wati.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.wati.school.dao.SalleRepository;
import fr.wati.school.entities.bean.Salle;

@Transactional
@Service
public class SalleService implements CrudService<Salle, Long> {

	@Autowired
	private SalleRepository salleRepository;
	
	public SalleService() {
	}

	@Override
	public <S extends Salle> S save(S entity) {
		return salleRepository.save(entity);
	}

	@Override
	public <S extends Salle> Iterable<S> save(Iterable<S> entities) {
		return salleRepository.save(entities);
	}

	@Override
	public Salle findOne(Long id) {
		return salleRepository.findOne(id);
	}

	@Override
	public boolean exists(Long id) {
		return salleRepository.exists(id);
	}

	@Override
	public Iterable<Salle> findAll() {
		return salleRepository.findAll();
	}

	@Override
	public Iterable<Salle> findAll(Iterable<Long> ids) {
		return salleRepository.findAll(ids);
	}

	@Override
	public long count() {
		return salleRepository.count();
	}

	@Override
	public void delete(Long id) {
		salleRepository.delete(id);
	}

	@Override
	public void delete(Salle entity) {
		salleRepository.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends Salle> entities) {
		salleRepository.delete(entities);
	}

	@Override
	public void deleteAll() {
		salleRepository.deleteAll();
	}

}
