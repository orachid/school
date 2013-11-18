
package fr.wati.school.web.rebirth.commons.navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ToStringBuilder;

public class SideNavItem {

    private String link;
    private String title;
    private String icon;
    @Valid
    private List<SideNavItem> submenu = new ArrayList<SideNavItem>();
    private String badge;
    private String badge_class;
    private String tooltip;
    private String tooltip_class;
    private boolean level1;
    private boolean level2;
    
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public SideNavItem withLink(String link) {
        this.link = link;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SideNavItem withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public SideNavItem withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public List<SideNavItem> getSubmenu() {
        return submenu;
    }

    public void setSubmenu(List<SideNavItem> submenu) {
        this.submenu = submenu;
    }

    public SideNavItem withSubmenu(List<SideNavItem> submenu) {
        this.submenu = submenu;
        return this;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public SideNavItem withBadge(String badge) {
        this.badge = badge;
        return this;
    }

    public String getBadge_class() {
        return badge_class;
    }

    public void setBadge_class(String badge_class) {
        this.badge_class = badge_class;
    }

    public SideNavItem withBadge_class(String badge_class) {
        this.badge_class = badge_class;
        return this;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public SideNavItem withTooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public String getTooltip_class() {
        return tooltip_class;
    }

    public void setTooltip_class(String tooltip_class) {
        this.tooltip_class = tooltip_class;
    }

    public SideNavItem withTooltip_class(String tooltip_class) {
        this.tooltip_class = tooltip_class;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	/**
	 * @return the level1
	 */
	public boolean isLevel1() {
		return level1;
	}

	/**
	 * @param level1 the level1 to set
	 */
	public void setLevel1(boolean level1) {
		this.level1 = level1;
	}

	/**
	 * @return the level2
	 */
	public boolean isLevel2() {
		return level2;
	}

	/**
	 * @param level2 the level2 to set
	 */
	public void setLevel2(boolean level2) {
		this.level2 = level2;
	}

}
