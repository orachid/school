package fr.wati.util;

import com.vaadin.server.ThemeResource;

public class IconProvider {
	
	private static final String ICONS_PATH="icons/app2/";
	private static final String _16X16="16x16/";
	private static final String _24X24="24x24/";
	private static final String _32X32="32x32/";
	private static final String _48X48="48x48/";

	private IconProvider() {
	}

	public static ThemeResource getResource(String resource){
		return null;
	}
	
	public static ThemeResource getIcone(String resource){
		return new ThemeResource(ICONS_PATH+"/"+resource);
	}
	
	public static ThemeResource getIcone16X16(String resource){
		return getIcone(_16X16+resource);
	}
	public static ThemeResource getIcone24X24(String resource){
		return getIcone(_24X24+resource);
	}
	public static ThemeResource getIcone32X32(String resource){
		return getIcone(_32X32+resource);
	}
	public static ThemeResource getIcone48X48(String resource){
		return getIcone(_48X48+resource);
	}
	
}
