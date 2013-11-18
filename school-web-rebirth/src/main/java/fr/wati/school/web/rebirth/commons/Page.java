/**
 * 
 */
package fr.wati.school.web.rebirth.commons;

/**
 * @author Rachid Ouattara
 *
 */
public class Page {

	private String title;
	private String content;
	private String description;
	private String inline_scripts;
	/**
	 * @param title
	 * @param description
	 * @param content 
	 * @param content 
	 */
	public Page(String title, String description,String inline_scripts, String content) {
		super();
		this.title = title;
		this.description = description;
		this.inline_scripts = inline_scripts;
		this.content = content;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the inline_scripts
	 */
	public String getInline_scripts() {
		return inline_scripts;
	}
	/**
	 * @param inline_scripts the inline_scripts to set
	 */
	public void setInline_scripts(String inline_scripts) {
		this.inline_scripts = inline_scripts;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
