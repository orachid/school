/**
 * 
 */
package fr.wati.scool.web.view.documents;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.vaadin.data.Container.Hierarchical;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.server.ThemeResource;

import fr.wati.school.entities.bean.Document;
import fr.wati.util.IconProvider;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
public class DocumentHierarchicalContainer implements Hierarchical {

	private Document rootDocument;
	public static final String ICON_PROPERTY="iconProperty"; 
	
	
	
	/**
	 * @param rootDocument
	 */
	public DocumentHierarchicalContainer(Document rootDocument) {
		super();
		this.rootDocument = rootDocument;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container#getItem(java.lang.Object)
	 */
	@Override
	public Item getItem(Object itemId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container#getContainerPropertyIds()
	 */
	@Override
	public Collection<String> getContainerPropertyIds() {
		List<String> properties=new ArrayList<>();
		properties.add("name");
		properties.add(ICON_PROPERTY);
		return properties;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container#getItemIds()
	 */
	@Override
	public Collection<?> getItemIds() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container#getContainerProperty(java.lang.Object, java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Property getContainerProperty(Object itemId, Object propertyId) {
		if(itemId!=null && itemId instanceof Document){
			Document document=(Document) itemId;
			if(ICON_PROPERTY.equals(propertyId)){
				if(document.equals(rootDocument)){
					return new ObjectProperty<ThemeResource>(IconProvider.getIcone16X16("diskdrive.png"));
				}
				if(document.isDirectory()){
					return new ObjectProperty<ThemeResource>(IconProvider.getIcone16X16("folder.png"));
				}else {
					return new ObjectProperty<ThemeResource>(IconProvider.getIcone16X16("page.png"));
				}
			}
			if(document.equals(rootDocument) && "name".equals(propertyId)){
				return new ObjectProperty<String>(document.getUserFullName());
			}
			PropertyDescriptor propertyDescriptor;
			try {
				propertyDescriptor = new PropertyDescriptor(String.valueOf(propertyId), Document.class);
				return new MethodProperty<>(propertyDescriptor.getPropertyType(), document,propertyDescriptor.getReadMethod(), propertyDescriptor.getWriteMethod());
			} catch (IntrospectionException e) {
				e.printStackTrace();
				return null;
			}
			
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container#getType(java.lang.Object)
	 */
	@Override
	public Class<?> getType(Object propertyId) {
		if(ICON_PROPERTY.equals(propertyId)){
			return ThemeResource.class;
		}
		return String.class;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container#size()
	 */
	@Override
	public int size() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container#containsId(java.lang.Object)
	 */
	@Override
	public boolean containsId(Object itemId) {
		return contentDocument(rootDocument, (Document)itemId);
	}

	private boolean contentDocument(Document rootDocument,Document documentToFind){
		if(rootDocument.equals(documentToFind)){
			return true;
		}
		if(rootDocument.isDirectory()){
			Iterator<Document> iterator = rootDocument.getDocuments().iterator();
			while (iterator.hasNext()) {
				Document document = (Document) iterator.next();
				if(documentToFind.equals(document)){
					return true;
				}
				if(contentDocument(document,documentToFind)){
					return true;
				}
			}
		}
		return false;
	}
	/* (non-Javadoc)
	 * @see com.vaadin.data.Container#addItem(java.lang.Object)
	 */
	@Override
	public Item addItem(Object itemId) throws UnsupportedOperationException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container#addItem()
	 */
	@Override
	public Object addItem() throws UnsupportedOperationException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container#addContainerProperty(java.lang.Object, java.lang.Class, java.lang.Object)
	 */
	@Override
	public boolean addContainerProperty(Object propertyId, Class<?> type,
			Object defaultValue) throws UnsupportedOperationException {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container#removeContainerProperty(java.lang.Object)
	 */
	@Override
	public boolean removeContainerProperty(Object propertyId)
			throws UnsupportedOperationException {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container#removeAllItems()
	 */
	@Override
	public boolean removeAllItems() throws UnsupportedOperationException {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container.Hierarchical#getChildren(java.lang.Object)
	 */
	@Override
	public Collection<?> getChildren(Object itemId) {
		List<Document> children=new ArrayList<>();
		if(itemId!=null && itemId instanceof Document){
			return ((Document)itemId).getDocuments();
		}
		return children;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container.Hierarchical#getParent(java.lang.Object)
	 */
	@Override
	public Object getParent(Object itemId) {
		 if (!(itemId instanceof Document)) {
	            return null;
	        }
	     return ((Document) itemId).getParent();
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container.Hierarchical#rootItemIds()
	 */
	@Override
	public Collection<Document> rootItemIds() {
		List<Document> documents=new ArrayList<>();
		documents.add(rootDocument);
		return documents;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container.Hierarchical#setParent(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean setParent(Object itemId, Object newParentId)
			throws UnsupportedOperationException {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container.Hierarchical#areChildrenAllowed(java.lang.Object)
	 */
	@Override
	public boolean areChildrenAllowed(Object itemId) {
		return ((Document)itemId).isDirectory();
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container.Hierarchical#setChildrenAllowed(java.lang.Object, boolean)
	 */
	@Override
	public boolean setChildrenAllowed(Object itemId, boolean areChildrenAllowed)
			throws UnsupportedOperationException {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container.Hierarchical#isRoot(java.lang.Object)
	 */
	@Override
	public boolean isRoot(Object itemId) {
		return ((Document)rootDocument).equals(rootDocument);
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container.Hierarchical#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(Object itemId) {
		if(itemId!=null && itemId instanceof Document){
			Document document=(Document) itemId;
			return !document.getDocuments().isEmpty();
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.data.Container.Hierarchical#removeItem(java.lang.Object)
	 */
	@Override
	public boolean removeItem(Object itemId)
			throws UnsupportedOperationException {
		return false;
	}

}
