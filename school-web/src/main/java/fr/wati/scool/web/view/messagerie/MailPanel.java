package fr.wati.scool.web.view.messagerie;

import com.vaadin.ui.HorizontalSplitPanel;


public class MailPanel extends HorizontalSplitPanel{
	
	private MailMenuPanel mailMenuPanel;
	private MailMainPanel mailMainPanel;
	
	public MailPanel(){
        setSplitPosition(20);
        mailMainPanel = new MailMainPanel(this);
        mailMenuPanel = new MailMenuPanel(this);
        addComponent(mailMenuPanel);
        addComponent(mailMainPanel);
        setHeight("100%");
	}

}