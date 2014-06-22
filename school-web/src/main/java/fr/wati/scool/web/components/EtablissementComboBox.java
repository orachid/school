/**
 * 
 */
package fr.wati.scool.web.components;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.provider.CachingLocalEntityProvider;
import com.vaadin.addon.jpacontainer.util.HibernateLazyLoadingDelegate;
import com.vaadin.ui.ComboBox;

import fr.wati.school.entities.bean.Etablissement;

/**
 * @author Rachid Ouattara
 * 
 */
@SuppressWarnings("serial")
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class EtablissementComboBox extends ComboBox {

	@Resource(name="crudEntityManager")
	private EntityManager entityManager;
	private JPAContainer<Etablissement> etablissementContainer;
	
	/**
	 * 
	 */
	public EtablissementComboBox() {
		super();
		setTextInputAllowed(false);
		setNullSelectionAllowed(false);
		setItemCaptionMode(ItemCaptionMode.PROPERTY);
		setItemCaptionPropertyId("code");
		setImmediate(true);
	}

	@PostConstruct
	public void postConstruct() {
		// Etablissement container
		CachingLocalEntityProvider<Etablissement> entityProvider = new CachingLocalEntityProvider<Etablissement>(
				Etablissement.class, entityManager);
		etablissementContainer = new JPAContainer<>(Etablissement.class);
		etablissementContainer.setEntityProvider(entityProvider);
		HibernateLazyLoadingDelegate hibernateLazyLoadingDelegate = new HibernateLazyLoadingDelegate();
		entityProvider.setLazyLoadingDelegate(hibernateLazyLoadingDelegate);
		setContainerDataSource(etablissementContainer);
		if(!etablissementContainer.getItemIds().isEmpty()){
			select(etablissementContainer.getIdByIndex(0));
		}
	}

	/**
	 * @return the etablissementContainer
	 */
	public JPAContainer<Etablissement> getEtablissementContainer() {
		return etablissementContainer;
	}

	/**
	 * @param etablissementContainer the etablissementContainer to set
	 */
	public void setEtablissementContainer(
			JPAContainer<Etablissement> etablissementContainer) {
		this.etablissementContainer = etablissementContainer;
	}

	
	
}
