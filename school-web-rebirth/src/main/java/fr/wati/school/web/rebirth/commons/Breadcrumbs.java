/**
 * 
 */
package fr.wati.school.web.rebirth.commons;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Rachid Ouattara
 * 
 */
public class Breadcrumbs {

	private String title = "unknown";
	private List<Link> links = new ArrayList<>();

	public Breadcrumbs(String title, List<Link> links) {
		super();
		this.title = title;
		this.links = links;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public static void main(String[] args) {
		PasswordEncoder encoder=new BCryptPasswordEncoder(11);
		System.out.println(encoder.encode("admin"));
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

	public static class Link {
		private String link;
		private String title;

		public Link(String link, String title) {
			super();
			this.link = link;
			this.title = title;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

	}
}
