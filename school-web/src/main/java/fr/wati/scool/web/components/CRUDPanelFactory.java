package fr.wati.scool.web.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import fr.wati.school.entities.bean.Entite;

@Component
public class CRUDPanelFactory {

	@Autowired
	private transient ApplicationContext applicationContext;
	public CRUDPanelFactory() {
	}

	@SuppressWarnings("unchecked")
	public <T extends Entite> DefaultCRUDPanel<T > getCRUDPanel(Class<T> entityClass,String entityName,String panelTitle){
		return (DefaultCRUDPanel<T>) applicationContext.getBean("defaultCRUDPanel", entityClass,entityName,panelTitle);
	}
}


