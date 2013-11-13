/**
 * 
 */
package fr.wati.school.services.documents;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import fr.wati.school.entities.bean.Document;
import fr.wati.school.entities.bean.Users;

/**
 * @author Rachid Ouattara
 * 
 */

public class DocumentManager implements ApplicationContextAware{

	@Value("${document.root.folder}")
	private String rootDocumentFolder;

	
	
	/**
	 * 
	 */
	public DocumentManager() {
		super();
	}

	public Document getUserDocument(String userName) {
		if (StringUtils.isEmpty(userName)) {
			throw new IllegalArgumentException("User must not be null");
		}
		String userRootFilePath = rootDocumentFolder + File.separator
				+ userName;
		File userRootFile = new File(userRootFilePath);
		if (!userRootFile.exists()) {
			userRootFile.mkdirs();
		}
		Document root = new Document();
		root.setDirectory(true);
		root.setLastModificationDate(new Date(userRootFile.lastModified()));
		root.setName("png");
		root.setDocumentPath(userRootFilePath);
		root.setDocuments(getSubStucture(root));
		return root;
	}

	private Set<Document> getSubStucture(Document document) {
		File currentFile = new File(document.getDocumentPath());
		if(currentFile.isDirectory()){
			Set<Document> documents = new HashSet<>();
			File[] listFiles = currentFile.listFiles();
			for(File file:listFiles){
				Document currentDocument = new Document();
				currentDocument.setName(file.getName());
				currentDocument.setDirectory(file.isDirectory());
				currentDocument.setDocumentPath(file.getPath());
				currentDocument.setParent(document);
				if (file.isDirectory()) {
					currentDocument.setDocuments(getSubStucture(currentDocument));
				}
				documents.add(currentDocument);
			}
			return documents;
		}
		return new HashSet<>();
	}
	
	/**
	 * @return the rootDocumentFolder
	 */
	public String getRootDocumentFolder() {
		return rootDocumentFolder;
	}

	/**
	 * @param rootDocumentFolder the rootDocumentFolder to set
	 */
	public void setRootDocumentFolder(String rootDocumentFolder) {
		this.rootDocumentFolder = rootDocumentFolder;
	}

	public static void main(String[] args) {
		DocumentManager documentManager=new DocumentManager();
		documentManager.setRootDocumentFolder("C:\\Users\\Rachid-home\\Downloads\\webdev2\\05\\shadow\\standart");
		Users user=new Users();
		user.setUsername("png");
		System.out.println(documentManager.getUserDocument("png"));
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		System.out.println();
	}
}
