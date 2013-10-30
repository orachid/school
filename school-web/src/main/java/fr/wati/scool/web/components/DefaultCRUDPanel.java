/**
 * 
 */
package fr.wati.scool.web.components;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

import fr.wati.school.entities.bean.Entite;

/**
 * @author Rachid Ouattara
 * 
 */
@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
@SuppressWarnings({ "deprecation", "serial" })
public class DefaultCRUDPanel<ENTITY extends Entite> extends CustomComponent
		implements ValueChangeListener, ClickListener {

	//private PagedFilterTable<JPAContainer<ENTITY>> entitiesTable;
	private Table entitiesTable;
	private JPAContainer<ENTITY> jpaContainer;
	private Class<ENTITY> entityClass;
	private Form editionForm;
	private Button addButton;
	private Button deleteButton;
	private Button commit;
	private Button discard;
	private Object[] formPropertyIds;
	@PersistenceContext
	private EntityManager entityManager;
	private HorizontalSplitPanel mainHorizontalLayout;
	private String entityName;
	private String title="";

	public DefaultCRUDPanel(Class<ENTITY> entityClass, String entityName,String title) {
		super();
		this.entityClass = entityClass;
		this.entityName = entityName;
		this.title = title;
	}

	@PostConstruct
	public void posConstruct() {
		//entitiesTable = new PagedFilterTable<>(entityName);
		entitiesTable = new Table(entityName);
		mainHorizontalLayout = new HorizontalSplitPanel();
		mainHorizontalLayout.setSizeFull();

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("school");
		// We need an entity manager to create entity provider
		EntityManager em = emf.createEntityManager();
		// We need an entity provider to create a container
		CachingMutableLocalEntityProvider<ENTITY> entityProvider = new CachingMutableLocalEntityProvider<ENTITY>(
				getEntityClass(), em);
		// And there we have it
		jpaContainer = new JPAContainer<ENTITY>(
				getEntityClass());
		jpaContainer.setEntityProvider(entityProvider);

		// We need an entity provider to create a container
		// EntityProvider entityProvider =(EntityProvider)
		// SpringSecurityViewProvider.applicationContext.getBean("transactionalCachingMutableLocalEntityProvider",
		// new Object[]{entityClass});
		// And there we have it
		jpaContainer = new JPAContainer<ENTITY>(entityClass);
		jpaContainer.setEntityProvider(entityProvider);
		entitiesTable.setContainerDataSource(jpaContainer);
		//entitiesTable.setFilterBarVisible(true);
		editionForm = new Form();
		editionForm.setCaption(getEntityClass().getSimpleName() + " editor");
		editionForm.addStyleName("bordered"); // Custom style
		editionForm.setBuffered(true);
		editionForm.setEnabled(false);

		commit = new Button("Save", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				editionForm.commit();
			}
		});
		commit.setStyleName(Reindeer.BUTTON_DEFAULT);

		discard = new Button("Cancel", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				editionForm.discard();
			}
		});

		editionForm.getFooter().addComponent(commit);
		editionForm.getFooter().addComponent(discard);
		editionForm.setVisible(false);

		VerticalLayout tablePanel = new VerticalLayout();

		mainHorizontalLayout.addComponent(tablePanel);
		mainHorizontalLayout.addComponent(editionForm);

		HorizontalLayout tableTopLayout = new HorizontalLayout();
		addButton = new Button("Add...", this);
		addButton.setDescription("Add new " + getEntityClass().getSimpleName());
		addButton.setStyleName(Reindeer.BUTTON_SMALL);
		tableTopLayout.addComponent(addButton);

		deleteButton = new Button("Delete", this);
		deleteButton.setDescription("Delete selected "
				+ getEntityClass().getSimpleName());
		deleteButton.setStyleName(Reindeer.BUTTON_SMALL);
		deleteButton.setEnabled(false);
		tableTopLayout.addComponent(deleteButton);
		tablePanel.addComponent(tableTopLayout);
		tablePanel.addComponent(entitiesTable);
		entitiesTable.setSelectable(true);
		entitiesTable.addValueChangeListener(this);
		entitiesTable.setImmediate(true);
		mainHorizontalLayout.setSplitPosition(60);
		VerticalLayout verticalLayout=new VerticalLayout();
		Label titleLabel=new Label(title);
		titleLabel.addStyleName("h4");
		verticalLayout.addComponent(titleLabel);
		verticalLayout.addComponent(mainHorizontalLayout);
		setCompositionRoot(verticalLayout);
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		Object itemId = event.getProperty().getValue();
		Item item = entitiesTable.getItem(itemId);
		boolean entitySelected = item != null;
		// modify visibility of form and delete button if an item is selected
		editionForm.setVisible(entitySelected);
		deleteButton.setEnabled(entitySelected);
		if (entitySelected) {
			editionForm.setEnabled(true);
			// set entity item to form and focus it
			editionForm.setItemDataSource(item,
					formPropertyIds != null ? Arrays.asList(formPropertyIds)
							: item.getItemPropertyIds());
			editionForm.focus();
		}
	}

	public void setVisibleTableProperties(Object... tablePropertyIds) {
		entitiesTable.setVisibleColumns(tablePropertyIds);
	}

	public void setVisibleFormProperties(Object... formPropertyIds) {
		this.formPropertyIds = formPropertyIds;
		editionForm.setVisibleItemProperties(formPropertyIds);
	}

	public String getCaption() {
		return getEntityClass().getSimpleName() + "s";
	}

	public Class<ENTITY> getEntityClass() {
		return entityClass;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == addButton) {
			addItem();
		} else if (event.getButton() == deleteButton) {
			deleteItem(entitiesTable.getValue());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	protected void addItem() {
		try {
			ENTITY newInstance = newInstance();
			Object itemId = jpaContainer.addEntity(newInstance);
			entitiesTable.setValue(itemId);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected ENTITY newInstance() throws InstantiationException,
			IllegalAccessException {
		ENTITY newInstance = getEntityClass().newInstance();
		return newInstance;
	}

	private void deleteItem(Object itemId) {
		jpaContainer.removeItem(itemId);
	}
}
