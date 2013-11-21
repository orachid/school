package fr.wati.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.wati.school.dao.EtudiantRepository;
import fr.wati.school.entities.bean.Etudiant;

@Transactional
@Service
public class StudentService implements CrudService<Etudiant, Long> {

	@Autowired
	private EtudiantRepository etudiantRepository;
	
	public StudentService() {
	}

	@Override
	public <S extends Etudiant> S save(S entity) {
		return etudiantRepository.save(entity);
	}

	@Override
	public <S extends Etudiant> Iterable<S> save(Iterable<S> entities) {
		return etudiantRepository.save(entities);
	}

	@Override
	public Etudiant findOne(Long id) {
		return etudiantRepository.findOne(id);
	}

	@Override
	public boolean exists(Long id) {
		return etudiantRepository.exists(id);
	}

	@Override
	public Iterable<Etudiant> findAll() {
		return etudiantRepository.findAll();
	}

	@Override
	public Iterable<Etudiant> findAll(Iterable<Long> ids) {
		return etudiantRepository.findAll(ids);
	}

	@Override
	public long count() {
		return etudiantRepository.count();
	}

	@Override
	public void delete(Long id) {
		etudiantRepository.delete(id);
	}

	@Override
	public void delete(Etudiant entity) {
		etudiantRepository.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends Etudiant> entities) {
		etudiantRepository.delete(entities);
	}

	@Override
	public void deleteAll() {
		etudiantRepository.deleteAll();
	}

}
