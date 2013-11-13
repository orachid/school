/**
 * 
 */
package fr.wati.scool.web.view.commons;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.addon.jpacontainer.util.HibernateLazyLoadingDelegate;
import com.vaadin.data.Item;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import fr.wati.scool.web.view.binding.CustomFieldFactory;

/**
 * @author Rachid Ouattara
 * 
 */
@SuppressWarnings({ "serial", "deprecation" })
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class EntityEditionForm<ENTITY> extends CustomComponent {

	private Button commit;
	private Button discard;
	private EntityItem<ENTITY> item;
	private Object[] formPropertyIds;
	private ENTITY entity;
	private JPAContainer<ENTITY> jpaContainer;
	private Object objectId;
	private Resolution dateResolution=Resolution.DAY;
	@Resource(name = "crudEntityManager")
	private EntityManager entityManager;
	private Runnable onCommitClick;
	private Runnable onDiscardClick;
	private Form editForm;
	

	/**
	 * @param item
	 * @param formPropertyIds
	 */
	public EntityEditionForm(String caption, ENTITY entity,
			Object[] formPropertyIds,JPAContainer<ENTITY> jpaContainer) {
		super();
		editForm=new Form();
		this.entity=entity;
		this.formPropertyIds = formPropertyIds;
		this.jpaContainer=jpaContainer;
	}
	
	public EntityEditionForm(String caption, ENTITY entity,
			Object[] formPropertyIds,JPAContainer<ENTITY> jpaContainer,Resolution dateResolution) {
		this(caption, entity, formPropertyIds, jpaContainer);
		this.dateResolution=dateResolution;
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostConstruct
	public void postConstruct() {
		if(jpaContainer==null){
			CachingMutableLocalEntityProvider entityProvider = new CachingMutableLocalEntityProvider(
					entity.getClass(), entityManager);
			// And there we have it
			this.jpaContainer = new JPAContainer(entity.getClass());
			this.jpaContainer.setEntityProvider(entityProvider);
			HibernateLazyLoadingDelegate hibernateLazyLoadingDelegate = new HibernateLazyLoadingDelegate();
			entityProvider.setLazyLoadingDelegate(hibernateLazyLoadingDelegate);
		}
		addStyleName("bordered"); // Custom style
		editForm.setBuffered(true);
		commit = new Button("Save", new Button.ClickListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void buttonClick(ClickEvent event) {
				editForm.commit();
				objectId= jpaContainer.addEntity(((JPAContainerItem<ENTITY>)editForm.getItemDataSource()).getEntity());
				if(onCommitClick!=null){
					onCommitClick.run();
				}
			}
		});
		 commit.addStyleName("wide");
         commit.addStyleName("default");

		discard = new Button("Cancel", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				editForm.discard();
				item=null;
				if(onDiscardClick!=null){
					onDiscardClick.run();
				}
			}
		});
		HorizontalLayout footer= new HorizontalLayout();
		
		footer.addStyleName("footer");
		footer.setWidth("100%");
		footer.setMargin(true);
		footer.setSpacing(true);
		
		footer.addComponent(discard);
		footer.setExpandRatio(discard, 1);
		footer.setComponentAlignment(discard,Alignment.TOP_RIGHT);
		 footer.addComponent(commit);
		// set entity item to form and focus it
		item=jpaContainer.createEntityItem(entity); 
		final CustomFieldFactory fieldFactory = new CustomFieldFactory(dateResolution);
		editForm.setFormFieldFactory(fieldFactory);
		editForm.setItemDataSource(
				item,
				formPropertyIds != null ? Arrays.asList(formPropertyIds) : item
						.getItemPropertyIds());
		editForm.setValidationVisibleOnCommit(true);
		editForm.focus();
		VerticalLayout mainVerticalLayout=new VerticalLayout();
		mainVerticalLayout.addComponent(editForm);
		mainVerticalLayout.addComponent(footer);
		setCompositionRoot(mainVerticalLayout);
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	public void setVisibleFormProperties(Object... formPropertyIds) {
		this.formPropertyIds = formPropertyIds;
		editForm.setVisibleItemProperties(formPropertyIds);
	}

	/**
	 * @return the objectId
	 */
	public Object getObjectId() {
		return objectId;
	}

	public Runnable getOnCommitClick() {
		return onCommitClick;
	}

	public void setOnCommitClick(Runnable onCommitClick) {
		this.onCommitClick = onCommitClick;
	}

	public Runnable getOnDiscardClick() {
		return onDiscardClick;
	}

	public void setOnDiscardClick(Runnable onDiscardClick) {
		this.onDiscardClick = onDiscardClick;
	}
	/**
	 * @return the jpaContainer
	 */
	public JPAContainer<ENTITY> getJpaContainer() {
		return jpaContainer;
	}
	
	
}
