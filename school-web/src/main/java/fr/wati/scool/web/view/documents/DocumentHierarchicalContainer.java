/**
 * 
 */
package fr.wati.scool.web.view.documents;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;

import com.vaadin.data.Container.Hierarchical;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.MethodProperty;

import fr.wati.school.entities.bean.Document;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
public class DocumentHierarchicalContainer implements Hierarchical {

	private Document rootDocument;
	
	
	
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
	public Collection<?> getContainerPropertyIds() {
		return null;
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
		return null;
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
				return contentDocument(document,documentToFind);
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

	public class DocumentItem implements Item{

		private Document document;
		
		
		/**
		 * @param document
		 */
		public DocumentItem(Document document) {
			super();
			this.document = document;
		}

		/* (non-Javadoc)
		 * @see com.vaadin.data.Item#getItemProperty(java.lang.Object)
		 */
		@Override
		public Property getItemProperty(Object id) {
			return null;
		}

		/* (non-Javadoc)
		 * @see com.vaadin.data.Item#getItemPropertyIds()
		 */
		@Override
		public Collection<?> getItemPropertyIds() {
			return null;
		}

		/* (non-Javadoc)
		 * @see com.vaadin.data.Item#addItemProperty(java.lang.Object, com.vaadin.data.Property)
		 */
		@Override
		public boolean addItemProperty(Object id, Property property)
				throws UnsupportedOperationException {
			return false;
		}

		/* (non-Javadoc)
		 * @see com.vaadin.data.Item#removeItemProperty(java.lang.Object)
		 */
		@Override
		public boolean removeItemProperty(Object id)
				throws UnsupportedOperationException {
			return false;
		}
		
	}
}
