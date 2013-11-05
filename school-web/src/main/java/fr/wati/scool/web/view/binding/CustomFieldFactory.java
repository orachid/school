package fr.wati.scool.web.view.binding;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Item;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;

@SuppressWarnings({ "serial", "unchecked" })
public class CustomFieldFactory extends FieldFactory {
	private Resolution dateResolution;

	public CustomFieldFactory(Resolution dateResolution) {
		super();
		this.dateResolution = dateResolution;
	}

	public CustomFieldFactory() {
	}

	@SuppressWarnings("rawtypes")
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
			if(fieldToReturn!=null && propertyId!=null && StringUtils.isNotEmpty(String.valueOf(propertyId))){
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

}
