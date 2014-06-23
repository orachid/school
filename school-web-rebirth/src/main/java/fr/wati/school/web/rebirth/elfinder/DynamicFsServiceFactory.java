package fr.wati.school.web.rebirth.elfinder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.bluejoe.elfinder.service.FsService;
import cn.bluejoe.elfinder.service.FsServiceFactory;

@Component("fsServiceFactory")
public class DynamicFsServiceFactory implements FsServiceFactory {

	@Autowired
	private UserFsVolumeProvider volumeProvider;
	
	@Override
	public FsService getFileService(HttpServletRequest paramHttpServletRequest,
			ServletContext paramServletContext) {
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(paramServletContext);
		SchoolFsService fsService = applicationContext.getBean(SchoolFsService.class);
		 
		 fsService.setVolumeProvider(volumeProvider);
		 return fsService;
	}

}
