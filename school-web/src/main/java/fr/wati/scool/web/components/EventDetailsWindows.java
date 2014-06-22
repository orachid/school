/**
 * 
 */
package fr.wati.scool.web.components;

import java.text.SimpleDateFormat;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.calendar.event.CalendarEvent;

/**
 * @author Rachid Ouattara
 * 
 */
public class EventDetailsWindows extends Window {

	/**
	 * @param calendarEvent
	 */
	public EventDetailsWindows(CalendarEvent calendarEvent) {
		VerticalLayout l = new VerticalLayout();
		l.setSpacing(true);

		setCaption(calendarEvent.getCaption());
		setContent(l);
		center();
		setCloseShortcut(KeyCode.ESCAPE, null);
		setResizable(false);
		setClosable(false);
		FormLayout fields = new FormLayout();
        fields.setWidth("35em");
        fields.setSpacing(true);
        fields.setMargin(true);
        SimpleDateFormat df = new SimpleDateFormat();
        Label label;
        //start date
        df.applyPattern("dd-MM-yyyy HH:mm");
        label = new Label(df.format(calendarEvent.getStart()));
        label.setSizeUndefined();
        label.setCaption("Start date");
        fields.addComponent(label);
        //end date
        label = new Label(df.format(calendarEvent.getEnd()));
        label.setSizeUndefined();
        label.setCaption("End date");
        fields.addComponent(label);
        //Caption
        label = new Label(calendarEvent.getCaption());
        label.setSizeUndefined();
        label.setCaption("Nom");
        fields.addComponent(label);
        //Details
        label = new Label(calendarEvent.getDescription());
        label.setSizeUndefined();
        label.setCaption("Description");
        fields.addComponent(label);
        
        l.addComponent(fields);
        HorizontalLayout footer = new HorizontalLayout();
        footer.addStyleName("footer");
        footer.setWidth("100%");
        footer.setMargin(true);

        Button ok = new Button("Close");
        ok.addStyleName("wide");
        ok.addStyleName("default");
        ok.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                close();
            }
        });
        footer.addComponent(ok);
        footer.setComponentAlignment(ok, Alignment.TOP_RIGHT);
        l.addComponent(footer);
	}

}
