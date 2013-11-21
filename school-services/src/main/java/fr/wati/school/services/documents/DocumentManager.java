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

import fr.wati.school.dao.UsersRepository;
import fr.wati.school.entities.bean.Document;
import fr.wati.school.entities.bean.Personne;
import fr.wati.school.entities.bean.Users;

/**
 * @author Rachid Ouattara
 * 
 */

public class DocumentManager {

	/**
	 * 
	 */
	private static final String PRIVATE = "private";

	/**
	 * 
	 */
	private static final String PUBLIC = "public";

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
		File userRootFile = createOrRetrieveUserDocumentsWorkspace(userName);
		Document root = new Document();
		root.setDirectory(true);
		root.setLastModificationDate(new Date(userRootFile.lastModified()));
		Users currentUsers = usersRepository.findByUsername(userName);
		root.setName(userName);
		root.setDocumentPath(userRootFile.getPath());
		root.setDocuments(getSubStucture(root));
		root.setUserFullName(((Personne) currentUsers).getFullName());
		return root;
	}

	public File createOrRetrieveUserDocumentsWorkspace(String userName) {
		String userRootFilePath = rootDocumentFolder + File.separator
				+ userName;
		File userRootFile = new File(userRootFilePath);
		if (!userRootFile.exists()) {
			userRootFile.mkdirs();
		}
		File privateFile = new File(userRootFilePath + File.separator
				+ PRIVATE);
		if (!privateFile.exists()) {
			privateFile.mkdirs();
		}
		File publicFile = new File(userRootFilePath + File.separator + PUBLIC);
		if (!publicFile.exists()) {
			publicFile.mkdirs();
		}
		return userRootFile;
	}

	private Set<Document> getSubStucture(Document document) {
		File currentFile = new File(document.getDocumentPath());
		if (currentFile.isDirectory()) {
			Set<Document> documents = new HashSet<>();
			File[] listFiles = currentFile.listFiles();
			for (File file : listFiles) {
				Document currentDocument = new Document();
				currentDocument.setName(file.getName());
				currentDocument.setLastModificationDate(new Date(file
						.lastModified()));
				currentDocument.setSize(file.getTotalSpace());
				currentDocument.setDirectory(file.isDirectory());
				currentDocument.setDocumentPath(file.getPath());
				currentDocument.setParent(document);
				if (file.isDirectory()) {
					currentDocument
							.setDocuments(getSubStucture(currentDocument));
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
	 * @param rootDocumentFolder
	 *            the rootDocumentFolder to set
	 */
	public void setRootDocumentFolder(String rootDocumentFolder) {
		this.rootDocumentFolder = rootDocumentFolder;
	}

	public static void main(String[] args) {
		DocumentManager documentManager = new DocumentManager();
		documentManager
				.setRootDocumentFolder("C:\\Users\\Rachid-home\\Downloads\\webdev2\\05\\shadow\\standart");
		Users user = new Users();
		user.setUsername("png");
		System.out.println(documentManager.getUserDocument("png"));
	}

}
