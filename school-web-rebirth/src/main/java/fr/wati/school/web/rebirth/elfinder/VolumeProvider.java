package fr.wati.school.web.rebirth.elfinder;

import java.util.List;

import cn.bluejoe.elfinder.service.FsVolume;
import fr.wati.school.entities.bean.Users;

public interface VolumeProvider {

	FsVolume getUserPrivateVolume(Users users);
	
	List<FsVolume> getSharedVolumes(Users users);
	
	List<FsVolume> getAllVolumesForUser(Users users);
}
