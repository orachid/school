/**
 * 
 */
package fr.wati.school.web.rebirth.utils;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.mustache.MustacheTemplateLoader;

/**
 * @author Rachid Ouattara
 * 
 */
@Component
public class ImprovedMustacheTemplateLoader extends MustacheTemplateLoader {

	InheritableThreadLocal<Map<String, String>> includedTemplatePath = new InheritableThreadLocal<Map<String, String>>() {
		/* (non-Javadoc)
		 * @see java.lang.ThreadLocal#initialValue()
		 */
		@Override
		protected Map<String, String> initialValue() {
			return new HashMap<String, String>();
		}

	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.view.mustache.MustacheTemplateLoader#
	 * getTemplate(java.lang.String)
	 */
	@Override
	public Reader getTemplate(String filename) throws Exception {
		try {
			return super.getTemplate(filename);
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		}
		// Second chance
		if(includedTemplatePath.get()!=null && includedTemplatePath.get().get(filename)!=null){
			return getTemplate(includedTemplatePath.get().get(filename));
		}
		throw new FileNotFoundException(filename);
	}

	/**
	 * @return the includedTemplatePath
	 */
	public InheritableThreadLocal<Map<String, String>> getIncludedTemplatePath() {
		return includedTemplatePath;
	}

}
