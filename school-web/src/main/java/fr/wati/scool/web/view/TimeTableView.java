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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import ru.xpoft.vaadin.VaadinMessageSource;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.provider.CachingLocalEntityProvider;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.addon.jpacontainer.util.HibernateLazyLoadingDelegate;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.event.Action;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClick;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClickHandler;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.CalendarEvent;

import fr.wati.school.entities.bean.Classe;
import fr.wati.school.entities.bean.ConseilleDeClasse;
import fr.wati.school.entities.bean.Cours;
import fr.wati.school.entities.bean.Evenement;
import fr.wati.school.entities.bean.Matiere;
import fr.wati.school.entities.bean.Reunion;
import fr.wati.school.services.utils.AnnotationUtils;
import fr.wati.scool.web.addons.ViewDescription;
import fr.wati.scool.web.components.EtablissementComboBox;
import fr.wati.scool.web.components.EvenementContainerEventProvider;
import fr.wati.scool.web.components.EventDetailsWindows;
import fr.wati.scool.web.view.commons.EntityEditionForm;

/**
 * @author Rachid Ouattara
 * 
 */
@ViewDescription(name = TimeTableView.NAME, requiredPermissions = "isAuthenticated()")
@SuppressWarnings("serial")
public class TimeTableView extends AbstractView {

	public static final String NAME = "timetable";
	@Resource(name = "crudEntityManager")
	private EntityManager entityManager;
	@Autowired
	private VaadinMessageSource messageSource;
	@Autowired
	private transient ApplicationContext applicationContext;
	private JPAContainer<Evenement> jpaContainer;
	private Calendar calendar;
	private HorizontalLayout filterHorizontalLayout;
	private ComboBox classeComboBox;
	private JPAContainer<Classe> classeContainer;
	private EtablissementComboBox etablissementComboBox;
	private OptionGroup matiereGroup;
	private JPAContainer<Matiere> matiereContainer;
	protected Window eventDetailsPopup;
	private Panel contentPanel;

	/**
	 * @param navigator
	 */
	public TimeTableView() {
		super();
	}

	@PostConstruct
	public void postConstruct() {
		VerticalLayout mainVerticalLayout = new VerticalLayout();
		buildCalendar();
		filterHorizontalLayout = buildFilterHorizontal();
		HorizontalLayout mainHorizontalLayout = new HorizontalLayout();
		VerticalLayout leftLayout = buildLeftFilter();
		
		//Add Filters
//		jpaContainer.addContainerFilter(new Compare.Equal(propertyId, value));
		
		
		mainHorizontalLayout.addComponent(leftLayout);
		VerticalLayout rightLayout = new VerticalLayout();
		rightLayout.addComponent(filterHorizontalLayout);

		

		rightLayout.addComponent(calendar);
		Panel globalLeftPanel=new Panel();
		globalLeftPanel.setContent(rightLayout);
		mainHorizontalLayout.addComponent(globalLeftPanel);
		mainVerticalLayout.addComponent(mainHorizontalLayout);
		mainVerticalLayout.setSizeFull();
		setCompositionRoot(mainVerticalLayout);
	}

	/**
	 * @return
	 */
	private VerticalLayout buildLeftFilter() {
		VerticalLayout layout = new VerticalLayout();
		layout.addStyleName("sidebar-menu");
		// Etablissement

		etablissementComboBox = applicationContext
				.getBean(EtablissementComboBox.class);
		etablissementComboBox.setCaption(null);
		// Classe container
		CachingLocalEntityProvider<Classe> entityProvider = new CachingLocalEntityProvider<Classe>(
				Classe.class, entityManager);
		classeContainer = new JPAContainer<>(
				Classe.class);
		classeContainer.setEntityProvider(entityProvider);
		HibernateLazyLoadingDelegate hibernateLazyLoadingDelegate = new HibernateLazyLoadingDelegate();
		entityProvider.setLazyLoadingDelegate(hibernateLazyLoadingDelegate);
		classeComboBox = new ComboBox();
		classeComboBox.setTextInputAllowed(false);
		classeComboBox.setNullSelectionAllowed(false);
		classeComboBox.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		classeComboBox.setItemCaptionPropertyId("nom");
		classeComboBox.setImmediate(true);
		classeContainer.setApplyFiltersImmediately(true);
		classeComboBox.setContainerDataSource(classeContainer);
		classeContainer.addContainerFilter(new Compare.Equal(
				"etablissement", etablissementComboBox.getEtablissementContainer()
						.getItem(etablissementComboBox.getValue()).getEntity()));
		etablissementComboBox.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event != null && event.getProperty() != null) {
					classeContainer.removeAllContainerFilters();
					classeContainer.addContainerFilter(new Compare.Equal(
							"etablissement", etablissementComboBox
									.getEtablissementContainer()
									.getItem(event.getProperty().getValue())
									.getEntity()));
					classeContainer.applyFilters();
					classeComboBox.setValue(classeContainer.firstItemId()!=null?classeContainer.firstItemId():null);
					filterCalendar();
				}
			}
		});
		classeContainer.applyFilters();
		classeComboBox.setValue(classeContainer.firstItemId()!=null?classeContainer.firstItemId():null);
		matiereGroup = new OptionGroup();
		CachingLocalEntityProvider<Matiere> matiereEntityProvider = new CachingLocalEntityProvider<Matiere>(
				Matiere.class, entityManager);
		matiereContainer = new JPAContainer<>(
				Matiere.class);
		matiereContainer.setEntityProvider(matiereEntityProvider);
		HibernateLazyLoadingDelegate matiereHibernateLazyLoadingDelegate = new HibernateLazyLoadingDelegate();
		matiereEntityProvider.setLazyLoadingDelegate(matiereHibernateLazyLoadingDelegate);
		matiereGroup.setContainerDataSource(matiereContainer);
		matiereGroup.setMultiSelect(true);
		matiereGroup.setImmediate(true);
		matiereGroup.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		matiereGroup.setItemCaptionPropertyId("code");
		final CheckBox selectAllCheckBox=new CheckBox("All");
		selectAllCheckBox.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				for(Object itemId:matiereGroup.getItemIds()){
					if(selectAllCheckBox.getValue()){
						matiereGroup.select(itemId);
					} else {
						matiereGroup.unselect(itemId);
					}
				}
				
			}
		});
		selectAllCheckBox.setVisible(false);
		matiereGroup.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				filterCalendar();
			}
		});
		matiereContainer.addContainerFilter(new Compare.Equal(
				"classe", classeContainer.getItem(classeComboBox.getValue())!=null?classeContainer.getItem(classeComboBox.getValue()).getEntity():null));
		classeComboBox.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event != null && event.getProperty() != null) {
					matiereContainer.removeAllContainerFilters();
					matiereContainer.addContainerFilter(new Compare.Equal(
							"classe", classeContainer.getItem(event.getProperty().getValue()).getEntity()));
					matiereContainer.applyFilters();
					if(!matiereContainer.getItemIds().isEmpty()){
						selectAllCheckBox.setVisible(true);
						selectAllCheckBox.setValue(true);
					}else{
						selectAllCheckBox.setVisible(false);
						selectAllCheckBox.setValue(false);
					}
					filterCalendar();
				}
			}
		});
		matiereContainer.applyFilters();
		
		Label matiereLabel=new Label("Matieres");
		Label etablissementLabel=new Label("Etablissements");
		Label classeLabel=new Label("Classes");
		
		
		layout.addComponent(etablissementLabel);
		layout.addComponent(etablissementComboBox);
		layout.addComponent(classeLabel);
		layout.addComponent(classeComboBox);
		layout.addComponent(matiereLabel);
		layout.addComponent(selectAllCheckBox);
		layout.addComponent(matiereGroup);

		return layout;
	}

	/**
	 * 
	 */
	protected void filterCalendar() {
	}

	/**
	 * 
	 */
	private void buildCalendar() {
		
		CachingMutableLocalEntityProvider<Evenement> entityProvider = new CachingMutableLocalEntityProvider<Evenement>(
				Evenement.class, entityManager);

		jpaContainer = new JPAContainer<>(Evenement.class);
		jpaContainer.setEntityProvider(entityProvider);

		HibernateLazyLoadingDelegate hibernateLazyLoadingDelegate = new HibernateLazyLoadingDelegate();
		entityProvider.setLazyLoadingDelegate(hibernateLazyLoadingDelegate);
		calendar = new Calendar(new EvenementContainerEventProvider(jpaContainer));
		calendar.setWidth("100%");
		calendar.setHeight("600px");
		calendar.setFirstVisibleHourOfDay(8);
		calendar.setLastVisibleHourOfDay(19);
		jpaContainer.sort(new String[] { "dateDebut" }, new boolean[] { true });
		calendar.addActionHandler(new Action.Handler() {

			@SuppressWarnings("rawtypes")
			@Override
			public void handleAction(Action action, Object sender, Object target) {
				System.out.println("Action: " + action.getCaption()
						+ " Sender: " + sender + " Target: " + target);
				if (action instanceof CreateEventAction
						&& target instanceof Date) {
					((CreateEventAction) action).setStartDate((Date) target);
					((CreateEventAction) action).run();
				}
			}

			@Override
			public Action[] getActions(Object target, Object sender) {
				List<Action> actions = new ArrayList<>();
				Action createCoursAction = new CreateEventAction<Cours>(
						jpaContainer, new Cours(), "Nouveau cours....");
				actions.add(createCoursAction);
				// Handle Right for each action
				Action createReunionAction = new CreateEventAction<Reunion>(
						jpaContainer, new Reunion(), "Nouvelle réunion....");
				actions.add(createReunionAction);
				Action createConseilleDeClasseAction = new CreateEventAction<ConseilleDeClasse>(
						jpaContainer, new ConseilleDeClasse(),
						"Nouveaux conseil de classe....");
				actions.add(createConseilleDeClasseAction);
				return actions.toArray(new Action[actions.size()]);
			}
		});
		calendar.setHandler(new EventClickHandler() {
			public void eventClick(EventClick event) {
				BasicEvent e = (BasicEvent) event.getCalendarEvent();
				// Do something with it
				
				getUI().removeWindow(eventDetailsPopup);
                buildEventDetailsPopup(event.getCalendarEvent());
                getUI().addWindow(eventDetailsPopup);
                eventDetailsPopup.focus();
			}
		});

	}

	/**
	 * @param calendarEvent
	 */
	protected void buildEventDetailsPopup(CalendarEvent calendarEvent) {
		eventDetailsPopup=new EventDetailsWindows(calendarEvent);
	}

	public HorizontalLayout buildFilterHorizontal() {
		HorizontalLayout filterHorizontalLayout = new HorizontalLayout();
		contentPanel = new Panel("Filters..");
		contentPanel.addStyleName("light");
		DateField startDateField = new DateField("From");
		startDateField.setValue(calendar.getStartDate());
		DateField endDateField = new DateField("To");
		endDateField.setValue(calendar.getEndDate());
		startDateField.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event != null && event.getProperty().getValue() != null
						&& event.getProperty().getValue() instanceof Date) {
					calendar.setStartDate((Date) event.getProperty().getValue());
				}
			}
		});
		startDateField.setImmediate(true);
		endDateField.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event != null && event.getProperty().getValue() != null
						&& event.getProperty().getValue() instanceof Date) {
					calendar.setEndDate((Date) event.getProperty().getValue());
				}
			}
		});
		endDateField.setImmediate(true);
		HorizontalLayout panelContent = new HorizontalLayout();
		panelContent.addComponent(startDateField);
		panelContent.addComponent(endDateField);
		contentPanel.setContent(panelContent);
		filterHorizontalLayout.addComponent(contentPanel);
		return filterHorizontalLayout;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.wati.scool.web.view.AbstractView#getViewName()
	 */
	@Override
	public String getViewName() {
		return NAME;
	}

	class CreateEventAction<ENTITY extends Evenement> extends Action implements
			Runnable {

		private Date startDate;
		private JPAContainer<Evenement> container;
		private String actionName;
		private ENTITY entity;
		private Object entityId;

		public CreateEventAction(JPAContainer<Evenement> container,
				ENTITY entity, String actionName) {
			this(actionName);
			this.container = container;
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
			java.util.Calendar calendar = java.util.Calendar.getInstance();
			calendar.setTime(startDate);
			calendar.add(java.util.Calendar.HOUR, 1);
			entity.setDateDebut(startDate);
			entity.setDateFin(calendar.getTime());
			// entityId = container.addEntity(entity);

			@SuppressWarnings("unchecked")
			final EntityEditionForm<ENTITY> editionForm = (EntityEditionForm<ENTITY>) applicationContext
					.getBean("entityEditionForm", getCaption(), entity,
							AnnotationUtils.getNonJPAPropertiesForCreation(entity.getClass()).toArray(), null, Resolution.MINUTE);
			final Window window=new Window(getCaption());
			window.setContent(editionForm);
			window.setModal(true);
			window.center();
			editionForm.setOnCommitClick(new Runnable() {
				@Override
				public void run() {
					jpaContainer.addEntity(editionForm.getJpaContainer().getItem(editionForm.getObjectId()).getEntity());
					window.close();
				}
			});
			editionForm.setOnDiscardClick(new Runnable() {
				@Override
				public void run() {
					window.close();
				}
			});
			getUI().addWindow(window);
		}

		/**
		 * @param startDate
		 *            the startDate to set
		 */
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
	}
	

}
