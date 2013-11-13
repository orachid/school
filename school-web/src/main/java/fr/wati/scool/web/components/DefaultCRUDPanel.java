/**
 * 
 */
package fr.wati.scool.web.components;

import java.lang.reflect.Field;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.addon.jpacontainer.util.HibernateLazyLoadingDelegate;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
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

import fr.wati.school.entities.annotations.ViewCaption;
import fr.wati.school.entities.bean.Entite;
import fr.wati.school.services.utils.AnnotationUtils;
import fr.wati.scool.web.view.binding.CustomFieldFactory;
import fr.wati.scool.web.view.commons.EntityEditionForm;

/**
 * @author Rachid Ouattara
 * 
 */
@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
@SuppressWarnings({ "deprecation", "serial","rawtypes" })
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
	private Object[] createFormPropertyIds;
	private Object[] tablePropertyIds;
	@Resource(name = "crudEntityManager")
	private EntityManager entityManager;
	@Autowired
	private transient ApplicationContext applicationContext;
	private HorizontalSplitPanel mainHorizontalLayout;
	private String entityName;
	private String title = "";
	private ClickListener addClickListener;

	public DefaultCRUDPanel(Class<ENTITY> entityClass, String entityName,String title) {
		this(entityClass, entityName, title, AnnotationUtils.getNonJPAPropertiesForCreation(entityClass).toArray(), AnnotationUtils.getNonJPAPropertiesForCreation(entityClass).toArray());
	}

	public DefaultCRUDPanel(Class<ENTITY> entityClass, String entityName,
			String title,Object[] createFormPropertyIds, Object[] tablePropertyIds) {
		super();
		this.entityClass = entityClass;
		this.entityName = entityName;
		this.title = title;
		this.createFormPropertyIds = createFormPropertyIds;
		this.tablePropertyIds = tablePropertyIds;

	}

	@PostConstruct
	public void posConstruct() {
		// entitiesTable = new PagedFilterTable<>(entityName);
		entitiesTable = new Table(entityName){

			/* (non-Javadoc)
			 * @see com.vaadin.ui.Table#formatPropertyValue(java.lang.Object, java.lang.Object, com.vaadin.data.Property)
			 */
			@SuppressWarnings("unchecked")
			@Override
			protected String formatPropertyValue(Object rowId, Object colId,
					Property<?> property) {
				Class<?> targetClass=property.getType();
				
				if (AnnotationUtils.hasAnyOfAnnotation(targetClass, Entity.class) && property.getValue()!=null) {
					String cationProperty= AnnotationUtils.getFirstPropertyNameWithAnnotation(ViewCaption.class, targetClass);
					try {
						if(StringUtils.isNotBlank(cationProperty)){
							Field field=targetClass.getDeclaredField(cationProperty);
							field.setAccessible(true);
							return String.valueOf(field.get(property.getValue()));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				 }
				return super.formatPropertyValue(rowId, colId, property);
			}
			
		};
		entitiesTable.addStyleName("striped");
		entitiesTable.setTableFieldFactory(new CustomFieldFactory());
		entitiesTable.setColumnCollapsingAllowed(true);
		
		
		mainHorizontalLayout = new HorizontalSplitPanel();
		mainHorizontalLayout.addStyleName("small");
		mainHorizontalLayout.setSizeFull();

		// EntityManagerFactory emf = Persistence
		// .createEntityManagerFactory("school");
		// // We need an entity manager to create entity provider
		// EntityManager em = emf.createEntityManager();
		// We need an entity provider to create a container
		CachingMutableLocalEntityProvider<ENTITY> entityProvider = new CachingMutableLocalEntityProvider<ENTITY>(
				getEntityClass(), entityManager);
		// And there we have it
		jpaContainer = new JPAContainer<ENTITY>(entityClass);
		jpaContainer.setEntityProvider(entityProvider);
		HibernateLazyLoadingDelegate hibernateLazyLoadingDelegate = new HibernateLazyLoadingDelegate();
		entityProvider.setLazyLoadingDelegate(hibernateLazyLoadingDelegate);
		entitiesTable.setContainerDataSource(jpaContainer);
		entitiesTable.setVisibleColumns(tablePropertyIds);
		// entitiesTable.setFilterBarVisible(true);
		editionForm = new Form();
		editionForm.setCaption(getEntityClass().getSimpleName() + " editor");
		editionForm.addStyleName("bordered"); // Custom style
		editionForm.setImmediate(true);
		editionForm.setBuffered(true);
		editionForm.setEnabled(false);
		final CustomFieldFactory fieldFactory = new CustomFieldFactory();
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
			Object[] updatePropertiesId=AnnotationUtils.getNonJPAPropertiesForCreation(((JPAContainerItem)item).getEntity().getClass()).toArray();
			editionForm.setEnabled(true);
			// set entity item to form and focus it
			editionForm.setItemDataSource(item,
					updatePropertiesId != null ? Arrays.asList(updatePropertiesId)
							: item.getItemPropertyIds());
			editionForm.focus();
		}
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
			if(getAddClickListener()!=null){
				getAddClickListener().buttonClick(event);
			}else {
				addItem();
			}
			
		} else if (event.getButton() == deleteButton) {
			deleteItem(entitiesTable.getValue());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	protected void addItem() {
		try {
			ENTITY newInstance = newInstance();
			@SuppressWarnings("unchecked")
			final EntityEditionForm<ENTITY> entityEditionForm = (EntityEditionForm<ENTITY>) applicationContext
					.getBean("entityEditionForm", editionForm.getCaption(),
							newInstance, createFormPropertyIds, jpaContainer);
			final Window window=new Window(editionForm.getCaption(), entityEditionForm);
			window.center();
			window.setModal(true);
			getUI().addWindow(window);
			entityEditionForm.setOnCommitClick(new Runnable() {
				@Override
				public void run() {
					window.close();
				}
			});
			entityEditionForm.setOnDiscardClick(new Runnable() {
				@Override
				public void run() {
					window.close();
				}
			});
			
			window.addCloseListener(new Window.CloseListener() {

				@Override
				public void windowClose(CloseEvent e) {
					if (entityEditionForm.getItem() != null
							&& entityEditionForm.getObjectId() != null) {
						entitiesTable.setValue(entityEditionForm.getObjectId());
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

	public ClickListener getAddClickListener() {
		return addClickListener;
	}

	public void setAddClickListener(ClickListener addClickListener) {
		this.addClickListener = addClickListener;
	}
	
	public void refresh(){
		jpaContainer.refresh();
	}
}
