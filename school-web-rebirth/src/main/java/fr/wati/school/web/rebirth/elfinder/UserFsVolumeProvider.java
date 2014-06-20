package fr.wati.school.web.rebirth.elfinder;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.bluejoe.elfinder.service.FsVolume;
import fr.wati.school.dao.EtablissementRepository;
import fr.wati.school.entities.bean.Etablissement;
import fr.wati.school.entities.bean.Etudiant;
import fr.wati.school.entities.bean.Personne;
import fr.wati.school.entities.bean.Professeur;
import fr.wati.school.entities.bean.Users;

@Component("UserFsVolumeProvider")
public class UserFsVolumeProvider extends SharedFsVolumeProvider {

	@Value("${document.root.folder}")
	private String rootDocumentFolder;
	
	@Autowired
	private EtablissementRepository etablissementRepository;
	
	@Override
	public FsVolume getUserPrivateVolume(Users users) {
		DefaultFsVolume fsVolume=new DefaultFsVolume();
		fsVolume.setName(((Personne)users).getUsername());
		String userPath = null;
		if(users instanceof Etudiant){
			Etablissement etablissement = etablissementRepository.findByClasses_Etudiants((Etudiant)users);
			userPath=etablissement.getCode()+File.separatorChar+users.getUsername()+"_"+users.getId();
		}else if (users instanceof Professeur) {
			userPath=users.getUsername()+"_"+users.getId();
		}
		
		String userRootFilePath = rootDocumentFolder + File.separator
				+ userPath;
		fsVolume.setRootDir(new File(userRootFilePath));
		return fsVolume;
	}

}
