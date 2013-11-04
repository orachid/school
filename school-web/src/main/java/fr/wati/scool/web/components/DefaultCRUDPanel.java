/**
 * 
 */
package fr.wati.scool.web.components;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.addon.jpacontainer.util.HibernateLazyLoadingDelegate;
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
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;

import fr.wati.school.entities.bean.Entite;
import fr.wati.scool.web.addons.SpringSecurityViewProvider;
import fr.wati.scool.web.view.commons.EntityEditionWindows;

/**
 * @author Rachid Ouattara
 * 
 */
@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
@SuppressWarnings({ "deprecation", "serial" })
public class DefaultCRUDPanel<ENTITY extends Entite> extends CustomComponent
		implements ValueChangeListener, ClickListener {

	// private PagedFilterTable<JPAContainer<ENTITY>> entitiesTable;
	private Table entitiesTable;
	private JPAContainer<ENTITY> jpaContainer;
	private Class<ENTITY> entityClass;
	private Form editionForm;
	private Button addButton;
	private Button deleteButton;
	private Button commit;
	private Button discard;
	private Object[] formPropertyIds;
	@Resource(name = "crudEntityManager")
	private EntityManager entityManager;
	private HorizontalSplitPanel mainHorizontalLayout;
	private String entityName;
	private String title = "";

	public DefaultCRUDPanel(Class<ENTITY> entityClass, String entityName,
			String title) {
		super();
		this.entityClass = entityClass;
		this.entityName = entityName;
		this.title = title;
	}

	@PostConstruct
	public void posConstruct() {
		// entitiesTable = new PagedFilterTable<>(entityName);
		entitiesTable = new Table(entityName);
		entitiesTable.setTableFieldFactory(new FieldFactory());
		mainHorizontalLayout = new HorizontalSplitPanel();
		mainHorizontalLayout.setSizeFull();

		// EntityManagerFactory emf = Persistence
		// .createEntityManagerFactory("school");
		// // We need an entity manager to create entity provider
		// EntityManager em = emf.createEntityManager();
		// We need an entity provider to create a container
		CachingMutableLocalEntityProvider<ENTITY> entityProvider = new CachingMutableLocalEntityProvider<ENTITY>(
				getEntityClass(), entityManager);
		// And there we have it
		jpaContainer = new JPAContainer<ENTITY>(getEntityClass());
		jpaContainer.setEntityProvider(entityProvider);

		// We need an entity provider to create a container
		// EntityProvider entityProvider =(EntityProvider)
		// SpringSecurityViewProvider.applicationContext.getBean("transactionalCachingMutableLocalEntityProvider",
		// new Object[]{entityClass});
		// And there we have it
		jpaContainer = new JPAContainer<ENTITY>(entityClass);
		jpaContainer.setEntityProvider(entityProvider);
		HibernateLazyLoadingDelegate hibernateLazyLoadingDelegate = new HibernateLazyLoadingDelegate();
		entityProvider.setLazyLoadingDelegate(hibernateLazyLoadingDelegate);
		entitiesTable.setContainerDataSource(jpaContainer);
		// entitiesTable.setFilterBarVisible(true);
		editionForm = new Form();
		editionForm.setCaption(getEntityClass().getSimpleName() + " editor");
		editionForm.addStyleName("bordered"); // Custom style
		editionForm.setBuffered(true);
		editionForm.setEnabled(false);
		final FieldFactory fieldFactory = new FieldFactory();
		editionForm.setFormFieldFactory(fieldFactory);

		commit = new Button("Save", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				editionForm.commit();
			}
		});
		commit.addStyleName("default");

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
		addButton.addStyleName("default");
		tableTopLayout.addComponent(addButton);

		deleteButton = new Button("Delete", this);
		deleteButton.setDescription("Delete selected "
				+ getEntityClass().getSimpleName());
		deleteButton.addStyleName("default");
		deleteButton.setEnabled(false);
		tableTopLayout.addComponent(deleteButton);
		tablePanel.addComponent(tableTopLayout);
		tablePanel.addComponent(entitiesTable);
		entitiesTable.setSelectable(true);
		entitiesTable.addValueChangeListener(this);
		entitiesTable.setImmediate(true);
		mainHorizontalLayout.setSplitPosition(60);
		VerticalLayout verticalLayout = new VerticalLayout();
		Label titleLabel = new Label(title);
		titleLabel.addStyleName("h4");
		verticalLayout.addComponent(titleLabel);
		verticalLayout.addComponent(mainHorizontalLayout);
		verticalLayout.setSizeFull();
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
			@SuppressWarnings("unchecked")
			final EntityEditionWindows<ENTITY> editionWindows = (EntityEditionWindows<ENTITY>) SpringSecurityViewProvider.applicationContext
					.getBean("entityEditionWindows", editionForm.getCaption(),
							newInstance, formPropertyIds,jpaContainer);
			getUI().addWindow(editionWindows);
			editionWindows.addCloseListener(new Window.CloseListener() {

				@Override
				public void windowClose(CloseEvent e) {
					if(editionWindows.getItem()!=null && editionWindows.getObjectId()!=null){
						entitiesTable.setValue(editionWindows.getObjectId());
					}
				}
			});
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
