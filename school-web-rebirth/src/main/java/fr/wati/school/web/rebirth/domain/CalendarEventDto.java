package fr.wati.school.web.rebirth.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CalendarEventDto implements Serializable{

	private Long id;
	private String title;
	private boolean allDay;
	private Date start;
	private Date end;
	private boolean editable;
	
	public CalendarEventDto() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public boolean isAllDay() {
		return allDay;
	}


	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}


	public Date getStart() {
		return start;
	}


	public void setStart(Date start) {
		this.start = start;
	}


	public Date getEnd() {
		return end;
	}


	public void setEnd(Date end) {
		this.end = end;
	}


	public boolean isEditable() {
		return editable;
	}


	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	
}
