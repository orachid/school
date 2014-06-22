package fr.wati.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.wati.school.dao.MatiereRepository;
import fr.wati.school.entities.bean.Matiere;

@Transactional
@Service
public class MatiereService implements CrudService<Matiere, Long> {

	@Autowired
	private MatiereRepository matiereRepository;
	
	public MatiereService() {
	}

	@Override
	public <S extends Matiere> S save(S entity) {
		return matiereRepository.save(entity);
	}

	@Override
	public <S extends Matiere> Iterable<S> save(Iterable<S> entities) {
		return matiereRepository.save(entities);
	}

	@Override
	public Matiere findOne(Long id) {
		return matiereRepository.findOne(id);
	}

	@Override
	public boolean exists(Long id) {
		return matiereRepository.exists(id);
	}

	@Override
	public Iterable<Matiere> findAll() {
		return matiereRepository.findAll();
	}

	@Override
	public Iterable<Matiere> findAll(Iterable<Long> ids) {
		return matiereRepository.findAll(ids);
	}

	@Override
	public long count() {
		return matiereRepository.count();
	}

	@Override
	public void delete(Long id) {
		matiereRepository.delete(id);
	}

	@Override
	public void delete(Matiere entity) {
		matiereRepository.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends Matiere> entities) {
		matiereRepository.delete(entities);
	}

	@Override
	public void deleteAll() {
		matiereRepository.deleteAll();
	}

}
