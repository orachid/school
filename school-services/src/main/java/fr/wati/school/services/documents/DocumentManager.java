/**
 * 
 */
package fr.wati.school.services.documents;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import fr.wati.school.entities.bean.Document;
import fr.wati.school.entities.bean.Personne;
import fr.wati.school.entities.bean.Users;
import fr.wati.school.services.dao.UsersRepository;

/**
 * @author Rachid Ouattara
 * 
 */

public class DocumentManager{

	@Value("${document.root.folder}")
	private String rootDocumentFolder;

	@Autowired
	private UsersRepository usersRepository;
	
	
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
		Users currentUsers=usersRepository.findByUsername(userName);
		root.setName(userName);
		root.setDocumentPath(userRootFilePath);
		root.setDocuments(getSubStucture(root));
		root.setUserFullName(((Personne)currentUsers).getFullName());
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
				currentDocument.setLastModificationDate(new Date(file.lastModified()));
				currentDocument.setSize(file.getTotalSpace());
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

}
