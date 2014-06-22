package fr.wati.scool.web.view.binding;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.addon.jpacontainer.fieldfactory.EmbeddedForm;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.MasterDetailEditor;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.NativeSelect;

import fr.wati.school.entities.annotations.ViewCaption;
import fr.wati.school.entities.annotations.ViewItemDescription;
import fr.wati.school.entities.bean.Civilite;
import fr.wati.school.services.utils.AnnotationUtils;

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
		comboBox.setTextInputAllowed(false);
		return comboBox;
	}

	 protected Field createManyToOneField(final EntityContainer containerForProperty,
	            final Object itemId, final Object propertyId, Component uiContext) {
	        final Class<?> type = containerForProperty.getType(propertyId);
	        final JPAContainer container = createJPAContainerFor(containerForProperty,
	                type, false);

	        final AbstractSelect nativeSelect = constructReferenceSelect(
	                containerForProperty, itemId, propertyId, uiContext, type);
	        nativeSelect.setMultiSelect(false);
	        nativeSelect.setCaption(DefaultFieldFactory
	                .createCaptionByPropertyId(propertyId));
	        nativeSelect.setItemCaptionMode(NativeSelect.ITEM_CAPTION_MODE_ITEM);
			String caption=AnnotationUtils.getFirstPropertyNameWithAnnotation(ViewCaption.class, type);
			if(StringUtils.isNotBlank(caption)){
				nativeSelect.setItemCaptionMode(ItemCaptionMode.PROPERTY);
				nativeSelect.setItemCaptionPropertyId(caption);
			}
//			computeFieldDescription(containerForProperty, itemId, propertyId,
//					type, nativeSelect);
			nativeSelect.addValueChangeListener(new ValueChangeListener() {
				
				@Override
				public void valueChange(ValueChangeEvent event) {
					String description=AnnotationUtils.getFirstPropertyNameWithAnnotation(ViewItemDescription.class, type);
					computeFieldDescription(container, event.getProperty().getValue(), description, type, nativeSelect);
				}
			});
	        nativeSelect.setContainerDataSource(container);
	        nativeSelect.setConverter(new SingleSelectConverter(nativeSelect));
	        return nativeSelect;
	    }

	/**
	 * @param containerForProperty
	 * @param itemId
	 * @param propertyId
	 * @param type
	 * @param nativeSelect
	 */
	private void computeFieldDescription(EntityContainer containerForProperty,
			Object itemId, Object propertyId, Class<?> type,
			AbstractSelect nativeSelect) {
		String description=AnnotationUtils.getFirstPropertyNameWithAnnotation(ViewItemDescription.class, type);
		if(StringUtils.isNotBlank(description)){
			try{
			Class<?> targetClass=type;
			java.lang.reflect.Field field=targetClass.getDeclaredField(description);
			field.setAccessible(true);
			if(containerForProperty.getItem(itemId)!=null){
				nativeSelect.setDescription(String.valueOf(field.get(containerForProperty.getItem(itemId).getEntity())));
			}else {
				nativeSelect.setDescription(null);
			}
			}catch(Exception exception){
				exception.printStackTrace();
			}
		}
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
		if(fieldToReturn instanceof ComboBox){
			
		}
		return fieldToReturn;
	}

	
	  protected Field createOneToManyField(EntityContainer containerForProperty,
	            Object itemId, Object propertyId, Component uiContext) {
	        return new MasterDetailEditor(this, containerForProperty, itemId,
	                propertyId, uiContext);
	    }
	  
	
}
