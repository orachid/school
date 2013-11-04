/**
 * 
 */
package fr.wati.scool.web.view.commons;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Form;
import com.vaadin.ui.Window;

/**
 * @author Rachid Ouattara
 * 
 */
@SuppressWarnings({ "serial", "deprecation" })
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class EntityEditionWindows<ENTITY> extends Window {

	private Form editionForm;
	private Button commit;
	private Button discard;
	private EntityItem<ENTITY> item;
	private Object[] formPropertyIds;
	private String caption;
	private ENTITY entity;
	private JPAContainer<ENTITY> jpaContainer;
	private Object objectId;
	

	/**
	 * @param item
	 * @param formPropertyIds
	 */
	public EntityEditionWindows(String caption, ENTITY entity,
			Object[] formPropertyIds,JPAContainer<ENTITY> jpaContainer) {
		super();
		this.caption = caption;
		this.entity=entity;
		this.formPropertyIds = formPropertyIds;
		this.jpaContainer=jpaContainer;
	}

	@PostConstruct
	public void postConstruct() {
		setCaption(caption);
		editionForm = new Form();
		editionForm.addStyleName("bordered"); // Custom style
		editionForm.setBuffered(true);
		editionForm.setEnabled(false);
		commit = new Button("Save", new Button.ClickListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void buttonClick(ClickEvent event) {
				editionForm.commit();
				close();
				objectId= jpaContainer.addEntity(((JPAContainerItem<ENTITY>)editionForm.getItemDataSource()).getEntity());
			}
		});
		commit.addStyleName("default");

		discard = new Button("Cancel", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				editionForm.discard();
				item=null;
				close();
			}
		});
		editionForm.getFooter().addComponent(commit);
		editionForm.getFooter().addComponent(discard);

		editionForm.setEnabled(true);
		// set entity item to form and focus it
		item=jpaContainer.createEntityItem(entity); 
		final FieldFactory fieldFactory = new FieldFactory();
		editionForm.setFormFieldFactory(fieldFactory);
		editionForm.setItemDataSource(
				item,
				formPropertyIds != null ? Arrays.asList(formPropertyIds) : item
						.getItemPropertyIds());
		editionForm.focus();
		center();
		setModal(true);
		setClosable(true);
		setContent(editionForm);
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	public void setVisibleFormProperties(Object... formPropertyIds) {
		this.formPropertyIds = formPropertyIds;
		editionForm.setVisibleItemProperties(formPropertyIds);
	}

	/**
	 * @return the objectId
	 */
	public Object getObjectId() {
		return objectId;
	}
	
	
}
