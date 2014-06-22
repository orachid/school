/**
 * 
 */
package fr.wati.school.web.rebirth.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.mustache.MustacheTemplateLoader;

/**
 * @author Rachid Ouattara
 * 
 */
@Component
public class ImprovedMustacheTemplateLoader extends MustacheTemplateLoader {

	public static final String ASYNC_CONTENT_PREFFIX="@AsyncContent(";
	public static final String ASYNC_CONTENT_SUFFIX=")";
	public static final Pattern ASYNC_CONTENT_PATTERN=Pattern.compile("@AsyncContent\\((.*?)\\)");
	public static final String ASYNC_URL_PREFFIX="@AsyncURL(";
	public static final String ASYNC_URL_SUFFIX=")";
	public static final Pattern ASYNC_URL_PATTERN=Pattern.compile("@AsyncURL\\((.*?)\\)");
	
	protected ResourceLoader resourceLoader;
	protected String prefix = "";
    protected String suffix = "";
	
	InheritableThreadLocal<Map<String, String>> includedTemplatePath = new InheritableThreadLocal<Map<String, String>>() {
		/* (non-Javadoc)
		 * @see java.lang.ThreadLocal#initialValue()
		 */
		@Override
		protected Map<String, String> initialValue() {
			return new HashMap<String, String>();
		}

	};
	public ImprovedMustacheTemplateLoader() {
		super();
	}
	
	public ImprovedMustacheTemplateLoader(ResourceLoader resourceLoader) {
		super();
		setResourceLoader(resourceLoader);
	}

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
			String computedFileName=filename;
			//Parent call overwriten
			 if (!filename.startsWith(prefix)) {
				 computedFileName = prefix + filename;
		        }
		        if (!filename.endsWith(suffix)) {
		        	computedFileName = computedFileName + suffix;
		        }
		        Resource resource = resourceLoader.getResource(computedFileName);
		        if (resource.exists()) {
		        	InputStream foundResourceInputStream=resource.getInputStream();		        	
		            return new InputStreamReader(processAndReplaceInInputStream(foundResourceInputStream));
		        }
		        throw new FileNotFoundException(computedFileName);
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		}
		// Second chance
		if(includedTemplatePath.get()!=null && includedTemplatePath.get().get(filename)!=null){
			Reader reader=getTemplate(includedTemplatePath.get().get(filename));
			return reader;
		}
		throw new FileNotFoundException(filename);
	}

	public InputStream processAndReplaceInInputStream(
			InputStream foundResourceInputStream) throws Exception {
			Iterator<String> iterator = IOUtils.readLines(foundResourceInputStream).iterator();
			StringBuffer buildContentBuffer=new StringBuffer();
			List<String> linesToReplace=new ArrayList<>();
			List<String> newlineValues=new ArrayList<>();
			List<AjaxAutoLoadElement> ajaxAutoLoadElements=new ArrayList<>();
			while (iterator.hasNext()) {
				String line = (String) iterator.next();
				buildContentBuffer.append(line);
				buildContentBuffer.append("\n");
				AjaxAutoLoadElement currentAjaxAutoLoadElement=!getAjaxAutoLoadElements(line).isEmpty()?getAjaxAutoLoadElements(line).get(0):null;
				if(currentAjaxAutoLoadElement!=null){
					ajaxAutoLoadElements.add(currentAjaxAutoLoadElement);
					if(!ajaxAutoLoadElements.isEmpty()){
						linesToReplace.add(line);
						newlineValues.add(JavaToScript.getDivPartJavaScriptForAjaxAutoLoadElements(currentAjaxAutoLoadElement));
					}
				}
				
			}
			String finalContent=buildContentBuffer.toString();
			
			if(!linesToReplace.isEmpty()){
				boolean alreadyReplaced=false;
				for (int i = 0; i < linesToReplace.size(); i++) {
					if(!alreadyReplaced){
						//we had the common part
						StringBuffer newContent=new StringBuffer();
						newContent.append(newlineValues.get(i));
						newContent.append(JavaToScript.getSecondPartJavaScriptForAjaxAutoLoadElements(ajaxAutoLoadElements.toArray(new AjaxAutoLoadElement[ajaxAutoLoadElements.size()])));
						newContent.append(JavaToScript.getCommonPartJavaScriptForAjaxAutoLoadElements());
						finalContent=finalContent.replace(linesToReplace.get(i), newContent.toString());
						alreadyReplaced=true;
					}else {
						finalContent=finalContent.replace(linesToReplace.get(i), newlineValues.get(i));
					}
					
				}
			}
			return IOUtils.toInputStream(finalContent);
			
	}

	/**
	 * @return the includedTemplatePath
	 */
	public InheritableThreadLocal<Map<String, String>> getIncludedTemplatePath() {
		return includedTemplatePath;
	}

	
	public static void main(String[] args) throws Exception {
	/*	ImprovedMustacheTemplateLoader loader=new ImprovedMustacheTemplateLoader(resourceLoader);
		loader.processAndReplaceInInputStream(new FileInputStream("c:\\toto.mustache"));*/
	}
	private List<AjaxAutoLoadElement> getAjaxAutoLoadElements(String line){
		List<AjaxAutoLoadElement> autoLoadElements=new ArrayList<>();
		String identifier=null;
		//String url=null;
		Matcher matcher = ASYNC_CONTENT_PATTERN.matcher(line);
		while (matcher.find()) {
	        identifier=matcher.group(1);
	    }
		
//	    matcher = ASYNC_URL_PATTERN.matcher(line);
//	    while (matcher.find()) {
//	        url=matcher.group(1);
//	    }
	    if(StringUtils.isNotEmpty(identifier)){
	    	AjaxAutoLoadElement ajaxAutoLoadElement=null;
	    	if(includedTemplatePath.get()!=null && includedTemplatePath.get().get(identifier)!=null){
	    		ajaxAutoLoadElement=new AjaxAutoLoadElement(identifier, includedTemplatePath.get().get(identifier));	
	 
	    	}
			autoLoadElements.add(ajaxAutoLoadElement);
	    }
		return autoLoadElements;
	}

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	/**
	 * @param resourceLoader the resourceLoader to set
	 */
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @param suffix the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getSuffix() {
		return suffix;
	}
	
//	 public void testTemplateLamda() throws MustacheException, IOException {
//		    MustacheBuilder c = new MustacheBuilder(root);
//		    Mustache m = c.parseFile("templatelambda.html");
//		    StringWriter sw = new StringWriter();
//		    FutureWriter writer = new FutureWriter(sw);
//		    m.execute(writer, new Scope(new Object() {
//		      String name = "Sam";
//		      TemplateFunction translate = new TemplateFunction() {
//		        @Override
//		        public String apply(String input) {
//		          if (input.equals("Hello {{name}}")) {
//		            return "{{name}}, Hola!";
//		          }
//		          return null;
//		        }
//		      };
//		    }));
//		    writer.flush();
//		    assertEquals(getContents(root, "templatelambda.txt"), sw.toString());
//		  }
}
