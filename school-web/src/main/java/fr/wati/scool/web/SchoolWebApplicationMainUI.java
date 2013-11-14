/**
 * 
 */
package fr.wati.scool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import fr.wati.scool.web.addons.SpringSecurityViewProvider;
import fr.wati.scool.web.components.HeaderHorizontalBar;
import fr.wati.scool.web.components.TopHorizontalMenuBar;
import fr.wati.scool.web.notification.Broadcaster;
import fr.wati.scool.web.notification.Broadcaster.BroadcastListener;
import fr.wati.scool.web.notification.PushEvent;
import fr.wati.scool.web.notification.PushEvent.PushEventType;

/**
 * @author Rachid Ouattara
 * 
 */
@Theme("school-webtheme")
@SuppressWarnings("serial")
@Push
@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SchoolWebApplicationMainUI extends UI implements ErrorHandler,
		BroadcastListener {
	@AutoGenerated
	private CssLayout mainLayout;
	@AutoGenerated
	private VerticalLayout globalVerticalLayout;
	@AutoGenerated
	private VerticalLayout middleVerticalLayout;
	@AutoGenerated
	private VerticalLayout menuBarVerticalLayout;
	private CssLayout content = new CssLayout();
	@Autowired
	private MainStatusBar mainStatusBar;
	private Navigator navigator;
	@Autowired
	private TopHorizontalMenuBar topHorizontalMenuBar;
	@Autowired
	private HeaderHorizontalBar headerHorizontalBar;

	@Override
	protected void init(final VaadinRequest request) {
		VaadinSession.getCurrent().setErrorHandler(this);
		setSizeFull();

		final ComponentContainerViewDisplay viewDisplay = new ComponentContainerViewDisplay(
				this.content);
		this.navigator = new Navigator(UI.getCurrent(), viewDisplay);
		this.navigator
				.addProvider(SpringSecurityViewProvider.createViewProvider(
						(Authentication) request.getUserPrincipal(), navigator));
		buildMainLayout();
		getPage().setTitle("School web app");

		final Button button = new Button("Broadcast");
		mainLayout.addComponent(button);
		button.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Broadcaster.broadcast(new PushEvent(getUI().getSession().getSession().getId(), PushEventType.CALENDAR, "Calendar updated"),PushEventType.CALENDAR);
			}
		});
		setContent(mainLayout);
		Broadcaster.register(this,PushEventType.ALL);
	}

	@Override
	public void detach() {
		Broadcaster.unregister(this,PushEventType.ALL);
		super.detach();
	}

	/**
	 * Exception on action
	 */
	@Override
	public void error(com.vaadin.server.ErrorEvent event) {
		// connector event
		if (event.getThrowable().getCause() instanceof AccessDeniedException) {
			AccessDeniedException accessDeniedException = (AccessDeniedException) event
					.getThrowable().getCause();
			Notification.show(accessDeniedException.getMessage(),
					Notification.Type.ERROR_MESSAGE);

			// Cleanup view. Now Vaadin ignores errors and always shows the
			// view. :-(
			// since beta10
			setContent(null);
			return;
		}

		// Error on page load. Now it doesn't work. User sees standard error
		// page.
		if (event.getThrowable() instanceof AccessDeniedException) {
			AccessDeniedException exception = (AccessDeniedException) event
					.getThrowable();

			Label label = new Label(exception.getMessage());
			label.setWidth(-1, Unit.PERCENTAGE);

			Link goToMain = new Link("Go to main", new ExternalResource("/"));

			VerticalLayout layout = new VerticalLayout();
			layout.addComponent(label);
			layout.addComponent(goToMain);
			layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
			layout.setComponentAlignment(goToMain, Alignment.MIDDLE_CENTER);

			VerticalLayout mainLayout = new VerticalLayout();
			mainLayout.setSizeFull();
			mainLayout.addComponent(layout);
			mainLayout.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);

			setContent(mainLayout);
			Notification.show(exception.getMessage(),
					Notification.Type.ERROR_MESSAGE);
			return;
		}

		DefaultErrorHandler.doDefault(event);
	}

	@AutoGenerated
	private CssLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new CssLayout();
		mainLayout.setImmediate(false);
		// mainLayout.addStyleName("root");
		mainLayout.setSizeFull();
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		// globalHorizontalLayout
		globalVerticalLayout = buildGlobalHorizontalLayout();
		mainLayout.addComponent(globalVerticalLayout);

		return mainLayout;
	}

	@AutoGenerated
	private VerticalLayout buildGlobalHorizontalLayout() {
		// common part: create layout
		globalVerticalLayout = new VerticalLayout();
		globalVerticalLayout.addStyleName("main-view");
		globalVerticalLayout.addComponent(headerHorizontalBar);
		globalVerticalLayout.addComponent(topHorizontalMenuBar);
		// globalVerticalLayout.setExpandRatio(topHorizontalMenuBar, 0.5f);
		// Content
		content.addStyleName("view-main-content");
		globalVerticalLayout.addComponent(content);
		// globalVerticalLayout.setExpandRatio(content, 7.5f);
		// Status bar
		// globalVerticalLayout.addComponent(mainStatusBar);
		// globalVerticalLayout.setExpandRatio(mainStatusBar, 0.5f);

		return globalVerticalLayout;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.wati.scool.web.addons.Broadcaster.BroadcastListener#receiveBroadcast
	 * (java.lang.String)
	 */
	@Override
	public void receiveBroadcast(final PushEvent pushEvent) {
		if(getUI().getSession().getSession().getId()!=String.valueOf(pushEvent.getSource())){
			access(new Runnable() {
				@Override
				public void run() {
					Notification n = new Notification("Message received",
							pushEvent.getMessage().toString(), Type.TRAY_NOTIFICATION);
					n.show(getPage());
				}
			});
		}
		
	}

	// @Configuration
	// @EnableLoadTimeWeaving(aspectjWeaving=AspectJWeaving.ENABLED)
	// @EnableSpringConfigured
	// @EnableAspectJAutoProxy
	// public static class TestConfiguration {
	// }
}
