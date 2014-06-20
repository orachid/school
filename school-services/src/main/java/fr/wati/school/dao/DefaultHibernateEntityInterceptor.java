package fr.wati.school.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class DefaultHibernateEntityInterceptor extends EmptyInterceptor {

	@Autowired
	private List<Interceptor> interceptors;
	
	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		boolean isModified=false;
		for(Interceptor interceptor:interceptors){
			//call on load on each interceptor
			if(interceptor.onLoad(entity, id, state, propertyNames, types)){
				isModified=true;
			}
		}
		return isModified;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		boolean isModified=false;
		for(Interceptor interceptor:interceptors){
			//call onSave on each interceptor
			if(interceptor.onSave(entity, id, state, propertyNames, types)){
				isModified=true;
			}
		}
		return isModified;
	}

	public List<Interceptor> getInterceptors() {
		return interceptors;
	}

	public void setInterceptors(List<Interceptor> interceptors) {
		this.interceptors = interceptors;
	}

	
}
