/**
 * 
 */
package fr.wati.school.entities.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rachid Ouattara
 *
 */
public class Document {

	private String name;
	private boolean directory;
	private Set<Document> documents=new HashSet<>();
	private Document parent;
	private long size;
	private transient String documentPath;
	private Date lastModificationDate;
	private String userFullName;
	
	
	public void addDocument(Document document){
		documents.add(document);
	}
	
	public void removeDocument(Document document){
		documents.remove(document);
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the directory
	 */
	public boolean isDirectory() {
		return directory;
	}
	/**
	 * @param directory the directory to set
	 */
	public void setDirectory(boolean directory) {
		this.directory = directory;
	}
	/**
	 * @return the documents
	 */
	public Set<Document> getDocuments() {
		return documents;
	}
	/**
	 * @param documents the documents to set
	 */
	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}
	/**
	 * @return the parent
	 */
	public Document getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(Document parent) {
		this.parent = parent;
	}

	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * @return the lastModificationDate
	 */
	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	/**
	 * @param lastModificationDate the lastModificationDate to set
	 */
	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	/**
	 * @return the documentPath
	 */
	public String getDocumentPath() {
		return documentPath;
	}

	/**
	 * @param documentPath the documentPath to set
	 */
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	/**
	 * @return the userFullName
	 */
	public String getUserFullName() {
		return userFullName;
	}

	/**
	 * @param userFullName the userFullName to set
	 */
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	
	
}
