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
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.addon.jpacontainer.util.HibernateLazyLoadingDelegate;
import com.vaadin.event.Action;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;

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
	
	/**
	 * @param navigator
	 */
	public TimeTableView() {
		super();
	}

	@PostConstruct
	public void postConstruct(){
		
		VerticalLayout mainVerticalLayout=new VerticalLayout();
		Label titleLabel=new Label("Calendier");
		titleLabel.addStyleName("h4");
		mainVerticalLayout.addComponent(titleLabel);
		HorizontalLayout mainHorizontalLayout=new HorizontalLayout();
		VerticalLayout leftLayout=new VerticalLayout();
		mainHorizontalLayout.addComponent(leftLayout);
		VerticalLayout rightLayout=new VerticalLayout();
		mainHorizontalLayout.addComponent(rightLayout);
		Calendar calendar=new Calendar();
		CachingMutableLocalEntityProvider<Evenement> entityProvider = new CachingMutableLocalEntityProvider<Evenement>(
				Evenement.class, entityManager);
		
		final JPAContainer<Evenement> jpaContainer=new JPAContainer<>(Evenement.class);
		jpaContainer.setEntityProvider(entityProvider);
		
		HibernateLazyLoadingDelegate hibernateLazyLoadingDelegate = new HibernateLazyLoadingDelegate();
		entityProvider.setLazyLoadingDelegate(hibernateLazyLoadingDelegate);
		calendar.setContainerDataSource(jpaContainer, "nom", "commentaire", "dateDebut", "dateFin", null);
		calendar.setFirstVisibleHourOfDay(8);
		calendar.setLastVisibleHourOfDay(19);
		calendar.addActionHandler(new Action.Handler() {
			
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
		rightLayout.addComponent(calendar);
		mainVerticalLayout.addComponent(mainHorizontalLayout);
		mainVerticalLayout.setSizeFull();
		setCompositionRoot(mainVerticalLayout);
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
			//entityId = container.addEntity(entity);
			
			@SuppressWarnings("unchecked")
			final EntityEditionWindows<ENTITY> editionWindows = (EntityEditionWindows<ENTITY>) SpringSecurityViewProvider.applicationContext
					.getBean("entityEditionWindows", getCaption(),
							entity, null,container);
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
