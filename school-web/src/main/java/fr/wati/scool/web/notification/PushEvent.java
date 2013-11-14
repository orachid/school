package fr.wati.scool.web.notification;

import java.util.EventObject;

@SuppressWarnings("serial")
public class PushEvent extends EventObject {

	
	private PushEventType eventType;
	private Object message;


	public PushEvent(Object sessionId,PushEventType eventType,Object message) {
		super(sessionId);
		this.setMessage(message);
		this.setEventType(eventType);
		
	}

	
	
	public PushEventType getEventType() {
		return eventType;
	}



	public void setEventType(PushEventType eventType) {
		this.eventType = eventType;
	}



	public Object getMessage() {
		return message;
	}



	public void setMessage(Object message) {
		this.message = message;
	}



	public static enum PushEventType{
		CALENDAR,MAIL,CHAT,DOCUMENT,ALL;
	}
}
