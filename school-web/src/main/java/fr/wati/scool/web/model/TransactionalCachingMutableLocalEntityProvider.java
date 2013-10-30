package fr.wati.scool.web.model;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.addon.jpacontainer.util.HibernateLazyLoadingDelegate;

import fr.wati.school.entities.bean.Users;
import fr.wati.school.services.dao.UsersRepository;

//@Repository
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class TransactionalCachingMutableLocalEntityProvider<T> extends
		CachingMutableLocalEntityProvider<T> {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private UsersRepository usersRepository;

	public TransactionalCachingMutableLocalEntityProvider(Class<T> entityClass) {
		super(entityClass);
	}

	public TransactionalCachingMutableLocalEntityProvider(Class<T> entityClass,
			EntityManager entityManager) {
		super(entityClass, entityManager);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	
	
	/* (non-Javadoc)
	 * @see com.vaadin.addon.jpacontainer.provider.MutableLocalEntityProvider#runInTransaction(java.lang.Runnable)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	protected void runInTransaction(Runnable operation) {
		super.runInTransaction(operation);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public T addEntity(T entity) {
//		return super.addEntity(entity);
		return (T) usersRepository.save((Users)entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void removeEntity(Object entityId) {
		super.removeEntity(entityId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public T updateEntity(T entity) {
		return super.updateEntity(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateEntityProperty(Object entityId, String propertyName,
			Object propertyValue) throws IllegalArgumentException {
		super.updateEntityProperty(entityId, propertyName, propertyValue);
	}

	@PostConstruct
	public void init() {
		setEntityManager(entityManager);
		/*
		 * The entity manager is transaction scoped, which means that the
		 * entities will be automatically detached when the transaction is
		 * closed. Therefore, we do not need to explicitly detach them.
		 */
		setEntitiesDetached(false);
		//add layloding delegate
		HibernateLazyLoadingDelegate hibernateLazyLoadingDelegate = new HibernateLazyLoadingDelegate();
		setLazyLoadingDelegate(hibernateLazyLoadingDelegate);
	}

}
