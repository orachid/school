package fr.wati.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.wati.school.dao.UsersRepository;
import fr.wati.school.entities.bean.Users;

@Transactional
@Service
public class UserService implements CrudService<Users, Long>{

	@Autowired
	private UsersRepository usersRepository;
	
	public UserService() {
	}

	@Override
	public <S extends Users> S save(S entity) {
		return usersRepository.save(entity);
	}

	@Override
	public <S extends Users> Iterable<S> save(Iterable<S> entities) {
		return usersRepository.save(entities);
	}

	@Override
	public Users findOne(Long id) {
		return usersRepository.findOne(id);
	}

	@Override
	public boolean exists(Long id) {
		return usersRepository.exists(id);
	}

	@Override
	public Iterable<Users> findAll() {
		return usersRepository.findAll();
	}

	@Override
	public Iterable<Users> findAll(Iterable<Long> ids) {
		return usersRepository.findAll(ids);
	}

	@Override
	public long count() {
		return usersRepository.count();
	}

	@Override
	public void delete(Long id) {
		usersRepository.delete(id);
	}

	@Override
	public void delete(Users entity) {
		usersRepository.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends Users> entities) {
		usersRepository.delete(entities);
	}

	@Override
	public void deleteAll() {
		usersRepository.deleteAll();
	}

	
	
}

