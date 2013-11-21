/**
 * 
 */
package fr.wati.school.web.rebirth.controller;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.ModelAndView;

import fr.wati.school.web.rebirth.commons.Breadcrumbs;
import fr.wati.school.web.rebirth.commons.Page;
import fr.wati.school.web.rebirth.commons.Paths;
import fr.wati.school.web.rebirth.commons.Site;

/**
 * @author Rachid Ouattara
 *
 */
public abstract class AbstractPageController {

		
	protected void processModelAndView(ModelAndView modelAndView){
		modelAndView.addObject("page", getPage());
		modelAndView.addObject("path", new Paths());
		modelAndView.addObject("site", new Site());
		modelAndView.addObject("breadcrumbs", getBreadcrumbs());
		
	}
	
	public abstract ResourceLoader getResourceLoader();
	public abstract String getTitle();
	public abstract String getDescription();
	public abstract String getContent();
	public abstract Breadcrumbs getBreadcrumbs();
	public abstract String[] getScripts();
	public abstract String[] getIe_scripts();
	public abstract String[] getStyles();
	
	/**
	 * paths comma "," separated to js resource
	 * Each path start from /WEB-INF/
	 **/
	abstract String getInline_scripts();
	
	
	public Page getPage(){
		//getResourceContent(getContentPath())
		return new Page(getTitle(), getDescription(),getInline_script_Content(),getContent(),getScripts(),getIe_scripts(),getStyles());
	}
	
	public String getInline_script_Content(){
		return getResourceContent(getInline_scripts());
		
	}
	
	public String getResourceContent(String resourcePath){
		StringBuffer scriptContent=new StringBuffer();
		if(StringUtils.isNotEmpty(resourcePath)){
			String[] jsFiles=resourcePath.split(",");
			for (int i = 0; i < jsFiles.length; i++) {
				String currentJs=jsFiles[i];
				try {
					scriptContent.append(IOUtils.toString(getResourceLoader().getResource("/WEB-INF/"+currentJs).getInputStream()));
				} catch (IOException e) {
					//No script found withe the given name
					e.printStackTrace();
				}
			}
		}
		return scriptContent.toString();
	}
}
