/**
 * 
 */
package fr.wati.school.web.rebirth.commons;

/**
 * @author Rachid Ouattara
 *
 */
public class Site {

	private String title="School Web Application";
	private String brand_icon="school-icon";
	private String brand_text="Beyond education";
	private String protocol="http:";
	private boolean remote_fonts;
	private boolean remote_jquery;

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
	 * @return the brand_icon
	 */
	public String getBrand_icon() {
		return brand_icon;
	}

	/**
	 * @param brand_icon the brand_icon to set
	 */
	public void setBrand_icon(String brand_icon) {
		this.brand_icon = brand_icon;
	}

	/**
	 * @return the brand_text
	 */
	public String getBrand_text() {
		return brand_text;
	}

	/**
	 * @param brand_text the brand_text to set
	 */
	public void setBrand_text(String brand_text) {
		this.brand_text = brand_text;
	}

	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public boolean isRemote_fonts() {
		return remote_fonts;
	}

	public void setRemote_fonts(boolean remote_fonts) {
		this.remote_fonts = remote_fonts;
	}

	public boolean isRemote_jquery() {
		return remote_jquery;
	}

	public void setRemote_jquery(boolean remote_jquery) {
		this.remote_jquery = remote_jquery;
	}
	
	
}
