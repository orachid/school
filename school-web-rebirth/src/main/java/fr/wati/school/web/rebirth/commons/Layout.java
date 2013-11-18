/**
 * 
 */
package fr.wati.school.web.rebirth.commons;

import java.util.ArrayList;
import java.util.List;

import fr.wati.school.web.rebirth.commons.navigation.SideNavItem;

/**
 * @author Rachid Ouattara
 *
 */
public class Layout {

	private List<SideNavItem> sidenav_navList=new ArrayList<>();

	/**
	 * @return the sidenav_navList
	 */
	public List<SideNavItem> getSidenav_navList() {
		return sidenav_navList;
	}

	/**
	 * @param sidenav_navList the sidenav_navList to set
	 */
	public void setSidenav_navList(List<SideNavItem> sidenav_navList) {
		this.sidenav_navList = sidenav_navList;
	}
	
	
}
