/**
 * 
 */
package fr.wati.scool.web.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.provider.CachingLocalEntityProvider;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.addon.jpacontainer.util.HibernateLazyLoadingDelegate;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.Action;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClick;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClickHandler;
import com.vaadin.ui.components.calendar.event.BasicEvent;

import fr.wati.school.entities.bean.Classe;
import fr.wati.school.entities.bean.ConseilleDeClasse;
import fr.wati.school.entities.bean.Cours;
import fr.wati.school.entities.bean.Evenement;
import fr.wati.school.entities.bean.Reunion;
import fr.wati.scool.web.addons.SpringSecurityViewProvider;
import fr.wati.scool.web.addons.ViewDescription;
import fr.wati.scool.web.view.commons.EntityEditionWindows;

/**
 * @author Rachid Ouattara
 *
 */
@ViewDescription(name = TimeTableView.NAME, requiredPermissions = "isAuthenticated()")
@SuppressWarnings("serial")
public class TimeTableView extends AbstractView {

	public static final String NAME="timetable"; 
	@Resource(name="crudEntityManager")
	private EntityManager entityManager;
	private JPAContainer<Evenement> jpaContainer;
	private Calendar calendar;
	private HorizontalLayout filterHorizontalLayout;
	
	/**
	 * @param navigator
	 */
	public TimeTableView() {
		super();
	}

	@PostConstruct
	public void postConstruct(){
		Label titleLabel=new Label("Calendier");
		titleLabel.addStyleName("h4");
		
		VerticalLayout mainVerticalLayout=new VerticalLayout();
		mainVerticalLayout.addComponent(titleLabel);
		filterHorizontalLayout = buildFilterHorizontal();
		
		buildCalendar();
		
		HorizontalLayout mainHorizontalLayout=new HorizontalLayout();
		VerticalLayout leftLayout=buildLeftFilter();
		mainHorizontalLayout.addComponent(leftLayout);
		VerticalLayout rightLayout=new VerticalLayout();
		rightLayout.addComponent(filterHorizontalLayout);
		
		mainHorizontalLayout.addComponent(rightLayout);
		
		rightLayout.addComponent(calendar);
		mainVerticalLayout.addComponent(mainHorizontalLayout);
		mainVerticalLayout.setSizeFull();
		setCompositionRoot(mainVerticalLayout);
	}

	/**
	 * @return
	 */
	private VerticalLayout buildLeftFilter() {
		VerticalLayout layout=new VerticalLayout();
		//Classe container
		CachingLocalEntityProvider<Classe> entityProvider = new CachingLocalEntityProvider<Classe>(Classe.class, entityManager);
		JPAContainer<Classe> classeContainer = new JPAContainer<>(Classe.class);
		classeContainer.setEntityProvider(entityProvider);
		HibernateLazyLoadingDelegate hibernateLazyLoadingDelegate = new HibernateLazyLoadingDelegate();
		entityProvider.setLazyLoadingDelegate(hibernateLazyLoadingDelegate);
		ComboBox classeComboBox=new ComboBox("Classe");
		classeComboBox.setContainerDataSource(classeContainer);
		layout.addComponent(classeComboBox);
		
		return layout;
	}

	/**
	 * 
	 */
	private void buildCalendar() {
		calendar = new Calendar();
		CachingMutableLocalEntityProvider<Evenement> entityProvider = new CachingMutableLocalEntityProvider<Evenement>(
				Evenement.class, entityManager);
		
		jpaContainer = new JPAContainer<>(Evenement.class);
		jpaContainer.setEntityProvider(entityProvider);
		
		HibernateLazyLoadingDelegate hibernateLazyLoadingDelegate = new HibernateLazyLoadingDelegate();
		entityProvider.setLazyLoadingDelegate(hibernateLazyLoadingDelegate);
		calendar.setContainerDataSource(jpaContainer, "nom", "commentaire", "dateDebut", "dateFin", null);
		calendar.setFirstVisibleHourOfDay(8);
		calendar.setLastVisibleHourOfDay(19);
		jpaContainer.sort(new String[]{"dateDebut"},  new boolean[]{true});
		calendar.addActionHandler(new Action.Handler() {
			
			@SuppressWarnings("rawtypes")
			@Override
			public void handleAction(Action action, Object sender, Object target) {
				System.out.println("Action: "+action.getCaption()+" Sender: "+sender+" Target: "+target);
				if(action instanceof CreateEventAction && target instanceof Date){
					((CreateEventAction) action).setStartDate((Date) target);
					((CreateEventAction) action).run();
				}
			}
			
			@Override
			public Action[] getActions(Object target, Object sender) {
				List<Action> actions=new ArrayList<>();
				Action createCoursAction=new CreateEventAction<Cours>(jpaContainer, new Cours(), "Nouveau cours....");
				actions.add(createCoursAction);
				//Handle Right for each action
				Action createReunionAction=new CreateEventAction<Reunion>(jpaContainer, new Reunion(), "Nouvelle réunion....");
				actions.add(createReunionAction);
				Action createConseilleDeClasseAction=new CreateEventAction<ConseilleDeClasse>(jpaContainer, new ConseilleDeClasse(), "Nouveaux conseil de classe....");
				actions.add(createConseilleDeClasseAction);
				return actions.toArray(new Action[actions.size()]);
			}
		});
		calendar.setHandler(new EventClickHandler() {
			 public void eventClick(EventClick event) {
				 BasicEvent e = (BasicEvent) event.getCalendarEvent();
				 // Do something with it
				 new Notification("Event clicked: " + e.getCaption(),
				 e.getDescription()).show(Page.getCurrent());
				 }
				});

	}
	
	public HorizontalLayout buildFilterHorizontal(){
		HorizontalLayout filterHorizontalLayout = new HorizontalLayout();
		Panel panel=new Panel("Filters..");
		DateField startDateField=new DateField("From");
		DateField endDateField=new DateField("To");
		startDateField.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(event!=null && event.getProperty().getValue()!=null && event.getProperty().getValue() instanceof Date){
					calendar.setStartDate((Date) event.getProperty().getValue());
				}
			}
		});
		startDateField.setImmediate(true);
		endDateField.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(event!=null && event.getProperty().getValue()!=null && event.getProperty().getValue() instanceof Date){
					calendar.setEndDate((Date) event.getProperty().getValue());
				}
			}
		});
		endDateField.setImmediate(true);
		HorizontalLayout panelContent=new HorizontalLayout();
		panelContent.addComponent(startDateField);
		panelContent.addComponent(endDateField);
		panel.setContent(panelContent);
		filterHorizontalLayout.addComponent(panel);
		return filterHorizontalLayout;
	}
	
	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.AbstractView#getViewName()
	 */
	@Override
	public String getViewName() {
		return NAME;
	}

	
	class CreateEventAction<ENTITY extends Evenement> extends Action implements Runnable{

		private Date startDate;
		private JPAContainer<Evenement> container;
		private String actionName;
		private ENTITY entity;
		private Object entityId;
		
		public CreateEventAction(JPAContainer<Evenement> container,ENTITY entity,String actionName) {
			this(actionName);
			this.container=container;
			this.entity = entity;
			this.actionName = actionName;
		}
		
		public CreateEventAction(String caption) {
			super(caption);
		}

		public CreateEventAction(String caption, com.vaadin.server.Resource icon) {
			super(caption, icon);
		}

	

		@Override
		public void run() {
			java.util.Calendar calendar=java.util.Calendar.getInstance();
			calendar.setTime(startDate);
			calendar.add(java.util.Calendar.HOUR, 1);
			entity.setDateDebut(startDate);
			entity.setDateFin(calendar.getTime());
			//entityId = container.addEntity(entity);
			
			@SuppressWarnings("unchecked")
			final EntityEditionWindows<ENTITY> editionWindows = (EntityEditionWindows<ENTITY>) SpringSecurityViewProvider.applicationContext
					.getBean("entityEditionWindows", getCaption(),
							entity, null,container,Resolution.MINUTE);
			getUI().addWindow(editionWindows);
			editionWindows.addCloseListener(new Window.CloseListener() {

				@Override
				public void windowClose(CloseEvent e) {
//					if(editionWindows.getItem()!=null && editionWindows.getObjectId()!=null){
//						entitiesTable.setValue(editionWindows.getObjectId());
//					}
				}
			});
			
		}

		/**
		 * @param startDate the startDate to set
		 */
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		
		
	}
	
}
