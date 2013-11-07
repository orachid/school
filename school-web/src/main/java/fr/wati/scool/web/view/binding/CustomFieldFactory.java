package fr.wati.scool.web.view.binding;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.addon.jpacontainer.fieldfactory.EmbeddedForm;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.MasterDetailEditor;
import com.vaadin.data.Item;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;

import fr.wati.school.entities.bean.Civilite;

@SuppressWarnings({ "serial", "unchecked" ,"rawtypes"})
public class CustomFieldFactory extends FieldFactory {
	private Resolution dateResolution;

	public CustomFieldFactory(Resolution dateResolution) {
		super();
		this.dateResolution = dateResolution;
		setMultiSelectType(Civilite.class, ComboBox.class);
		
	}

	public CustomFieldFactory() {
		this(Resolution.DAY);
		
	}

	
	
	/* (non-Javadoc)
	 * @see com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory#constructReferenceSelect(com.vaadin.addon.jpacontainer.EntityContainer, java.lang.Object, java.lang.Object, com.vaadin.ui.Component, java.lang.Class)
	 */
	@Override
	protected AbstractSelect constructReferenceSelect(
			EntityContainer containerForProperty, Object itemId,
			Object propertyId, Component uiContext, Class<?> type) {
		ComboBox comboBox=new ComboBox(); 
		return comboBox;
	}

	@Override
	protected Field configureBasicFields(Field field) {
		Field fieldToreturn = super.configureBasicFields(field);
		if (Date.class.isAssignableFrom(fieldToreturn.getType())) {
			((DateField) fieldToreturn).setResolution(dateResolution);
		}
		return fieldToreturn;
	}

	
	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		Field fieldToReturn = super.createField(item, propertyId, uiContext);
		// Add validation
		if(item instanceof JPAContainerItem){
			JPAContainerItem jpaContainerItem=(JPAContainerItem) item;
			if(fieldToReturn!=null && propertyId!=null && StringUtils.isNotEmpty(String.valueOf(propertyId)) && !(fieldToReturn instanceof EmbeddedForm)){
				BeanValidator validator = new BeanValidator(jpaContainerItem.getEntity().getClass(),
						String.valueOf(propertyId));
				fieldToReturn.addValidator(validator);
				if (fieldToReturn.getLocale() != null) {
					validator.setLocale(fieldToReturn.getLocale());
				}
			}
		}
		return fieldToReturn;
	}

	
	  protected Field createOneToManyField(EntityContainer containerForProperty,
	            Object itemId, Object propertyId, Component uiContext) {
	        return new MasterDetailEditor(this, containerForProperty, itemId,
	                propertyId, uiContext);
	    }
	
}
