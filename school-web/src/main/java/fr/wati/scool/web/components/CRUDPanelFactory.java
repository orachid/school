package fr.wati.scool.web.components;

import org.springframework.stereotype.Component;

import fr.wati.school.entities.bean.Entite;
import fr.wati.scool.web.addons.SpringSecurityViewProvider;

@Component
public class CRUDPanelFactory {

	public CRUDPanelFactory() {
	}

	@SuppressWarnings("unchecked")
	public <T extends Entite> DefaultCRUDPanel<T > getCRUDPanel(Class<T> entityClass,String entityName,String panelTitle){
		return (DefaultCRUDPanel<T>) SpringSecurityViewProvider.applicationContext.getBean("defaultCRUDPanel", entityClass,entityName,panelTitle);
	}
}


