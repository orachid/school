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
	private String[] scripts;
	private String[] ie_scripts;
	private String[] styles;
	private String[] inline_styles;

	/**
	 * @param title
	 * @param description
	 * @param content
	 * @param content
	 */
	public Page(String title, String description, String inline_scripts,
			String content, String[] scripts, String[] ie_scripts,
			String[] styles) {
		super();
		this.title = title;
		this.description = description;
		this.inline_scripts = inline_scripts;
		this.content = content;
		this.scripts = scripts;
		this.ie_scripts = ie_scripts;
		this.styles = styles;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
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
	 * @param description
	 *            the description to set
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
	 * @param inline_scripts
	 *            the inline_scripts to set
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
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public String[] getScripts() {
		return scripts;
	}

	public void setScripts(String[] scripts) {
		this.scripts = scripts;
	}

	public String[] getIe_scripts() {
		return ie_scripts;
	}

	public void setIe_scripts(String[] ie_scripts) {
		this.ie_scripts = ie_scripts;
	}

	/**
	 * @return the styles
	 */
	public String[] getStyles() {
		return styles;
	}

	/**
	 * @param styles the styles to set
	 */
	public void setStyles(String[] styles) {
		this.styles = styles;
	}

	public String[] getInline_styles() {
		return inline_styles;
	}

	public void setInline_styles(String[] inline_styles) {
		this.inline_styles = inline_styles;
	}

}
