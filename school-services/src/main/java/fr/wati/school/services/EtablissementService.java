package fr.wati.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.wati.school.dao.EtablissementRepository;
import fr.wati.school.entities.bean.Etablissement;

@Transactional
@Service
public class EtablissementService implements CrudService<Etablissement, Long> {

	@Autowired
	private EtablissementRepository etablissementRepository;
	
	public EtablissementService() {
	}

	@Override
	public <S extends Etablissement> S save(S entity) {
		return etablissementRepository.save(entity);
	}

	@Override
	public <S extends Etablissement> Iterable<S> save(Iterable<S> entities) {
		return etablissementRepository.save(entities);
	}

	@Override
	public Etablissement findOne(Long id) {
		return etablissementRepository.findOne(id);
	}

	@Override
	public boolean exists(Long id) {
		return etablissementRepository.exists(id);
	}

	@Override
	public Iterable<Etablissement> findAll() {
		return etablissementRepository.findAll();
	}

	@Override
	public Iterable<Etablissement> findAll(Iterable<Long> ids) {
		return etablissementRepository.findAll(ids);
	}

	@Override
	public long count() {
		return etablissementRepository.count();
	}

	@Override
	public void delete(Long id) {
		etablissementRepository.delete(id);
	}

	@Override
	public void delete(Etablissement entity) {
		etablissementRepository.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends Etablissement> entities) {
		etablissementRepository.delete(entities);
	}

	@Override
	public void deleteAll() {
		etablissementRepository.deleteAll();
	}

}
