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

	private SideNavItem[] sidenav_navList;
	public static Layout rootLayout=buildLayoutDefault();
	private static SideNavItem biblioNavItem;
	private static SideNavItem documentNavItem;
	private static SideNavItem messagerieNavItem;
	private static SideNavItem calendarNavItem;
	private static SideNavItem sallesEditionItem;
	private static SideNavItem classesEditionItem;
	private static SideNavItem matiereEditionItem;
	private static SideNavItem etablissementEditionItem;
	private static SideNavItem userEditionItem;
	private static SideNavItem adminEditionNavItem;
	private static SideNavItem adminNavItem;
	private static SideNavItem dashboardNavItem;
	private static SideNavItem batchNavItem;
	private static SideNavItem appSettingsNavItem;

	/**
	 * @return the sidenav_navList
	 */
	public SideNavItem[] getSidenav_navList() {
		return sidenav_navList;
	}

	/**
	 * @param sidenav_navList the sidenav_navList to set
	 */
	public void setSidenav_navList(SideNavItem[] sidenav_navList) {
		this.sidenav_navList = sidenav_navList;
	}
	
	public static Layout buildLayoutDefault(){
		Layout layout=new Layout();
		List<SideNavItem> superNavItems=new ArrayList<>();
		
		dashboardNavItem = new SideNavItem("DashBoard", "/", "icon-dashboard", false, true);
		dashboardNavItem.setLevel1(true);
		dashboardNavItem.setLevel2(false);
		superNavItems.add(dashboardNavItem);
		adminNavItem = new SideNavItem("Admin", "#", "icon-admin", true, false);
		adminNavItem.setLevel1(true);
		adminNavItem.setLevel2(false);
		superNavItems.add(adminNavItem);
		List<SideNavItem> adminSubItems=new ArrayList<>();
		//Admin Edition
		adminEditionNavItem = new SideNavItem("Edition", "#", null, true, false);
		adminEditionNavItem.setLevel1(false);
		adminEditionNavItem.setLevel2(true);
		//Admin edition submenu
		List<SideNavItem> adminEditionSubItems=new ArrayList<>();
		userEditionItem = new SideNavItem("Users edition", "/users", null, false, true);
		userEditionItem.setLevel1(false);
		userEditionItem.setLevel2(true);
		adminEditionSubItems.add(userEditionItem);
		
		etablissementEditionItem = new SideNavItem("Etablissement", "/etablissements", null, false, true);
		etablissementEditionItem.setLevel1(false);
		etablissementEditionItem.setLevel2(true);
		adminEditionSubItems.add(etablissementEditionItem);
		
		matiereEditionItem = new SideNavItem("Matieres", "/matieres", null, false, true);
		matiereEditionItem.setLevel1(false);
		matiereEditionItem.setLevel2(true);
		adminEditionSubItems.add(matiereEditionItem);
		
		classesEditionItem = new SideNavItem("Classes", "/classes", null, false, true);
		classesEditionItem.setLevel1(false);
		classesEditionItem.setLevel2(true);
		adminEditionSubItems.add(classesEditionItem);
		
		sallesEditionItem = new SideNavItem("Salles", "/salles", null, false, true);
		sallesEditionItem.setLevel1(false);
		sallesEditionItem.setLevel2(true);
		adminEditionSubItems.add(sallesEditionItem);
		
		
		adminEditionNavItem.setSubmenu(adminEditionSubItems.toArray(new SideNavItem[adminEditionSubItems.size()]));
		
		adminSubItems.add(adminEditionNavItem);
		//Batch
		batchNavItem = new SideNavItem("Batch", "/batch", null, false, true);
		adminSubItems.add(batchNavItem);
		
		//Global settings
		appSettingsNavItem = new SideNavItem("Global settings", "/app-settings", null, false, true);
		adminSubItems.add(appSettingsNavItem);
				
		//Add Admin sub menus
		adminNavItem.setSubmenu(adminSubItems.toArray(new SideNavItem[adminSubItems.size()]));
		
		calendarNavItem = new SideNavItem("Emploie de temps", "/calendar", "icon-calendar", false, true);
		calendarNavItem.setLevel1(true);
		calendarNavItem.setLevel2(false);
		superNavItems.add(calendarNavItem);
		
		messagerieNavItem = new SideNavItem("Messages", "/messages", "icon-mail", false, true);
		messagerieNavItem.setLevel1(true);
		messagerieNavItem.setLevel2(false);
		superNavItems.add(messagerieNavItem);
		
		documentNavItem = new SideNavItem("Documents", "/documents", "icon-folder-close", false, true);
		documentNavItem.setLevel1(true);
		documentNavItem.setLevel2(false);
		superNavItems.add(documentNavItem);
		
		biblioNavItem = new SideNavItem("Bibliotheque", "/biblio", "icon-admin", false, true);
		biblioNavItem.setLevel1(true);
		biblioNavItem.setLevel2(false);
		superNavItems.add(biblioNavItem);
		layout.setSidenav_navList(superNavItems.toArray(new SideNavItem[superNavItems.size()]));
		return layout;
	}
	
	
	public static void active(String activeItem){
		switch (activeItem) {
		case "/users":
			userEditionItem.setClazz("active");
			adminEditionNavItem.setClazz("active open");
			adminNavItem.setClazz("active open");
			break;
		case "/etablissements":
			etablissementEditionItem.setClazz("active");
			adminEditionNavItem.setClazz("active open");
			adminNavItem.setClazz("active open");
			break;
		case "/classes":
			classesEditionItem.setClazz("active");
			adminEditionNavItem.setClazz("active open");
			adminNavItem.setClazz("active open");
			break;
		case "/salles":
			sallesEditionItem.setClazz("active");
			adminEditionNavItem.setClazz("active open");
			adminNavItem.setClazz("active open");
			break;
		case "/matieres":
			matiereEditionItem.setClazz("active");
			adminEditionNavItem.setClazz("active open");
			adminNavItem.setClazz("active open");
			break;
		case "/calendar":
			calendarNavItem.setClazz("active");
			break;
		case "/batch":
			batchNavItem.setClazz("active");
			adminNavItem.setClazz("active open");
			break;
		case "/app-settings":
			appSettingsNavItem.setClazz("active");
			adminNavItem.setClazz("active open");
			break;
		default:
			break;
		}
		
	}
}
