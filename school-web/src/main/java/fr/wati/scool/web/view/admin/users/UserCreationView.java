package fr.wati.scool.web.view.admin.users;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.addon.jpacontainer.util.HibernateLazyLoadingDelegate;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Form;

import fr.wati.school.entities.bean.Etablissement;
import fr.wati.school.entities.bean.Etudiant;
import fr.wati.school.entities.bean.Parent;
import fr.wati.school.entities.bean.Professeur;
import fr.wati.school.entities.bean.Users;
import fr.wati.school.services.utils.AnnotationUtils;
import fr.wati.scool.web.components.CustomWizard;
import fr.wati.scool.web.components.EtablissementComboBox;
import fr.wati.scool.web.view.binding.CustomFieldFactory;

@SuppressWarnings({ "serial", "deprecation","rawtypes","unchecked" })
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UserCreationView extends CustomComponent implements WizardProgressListener {

	@Autowired
	private transient ApplicationContext applicationContext;
	@Resource(name = "crudEntityManager")
	private EntityManager entityManager;
	private JPAContainer jpaContainer;
	private EntityItem beanItem;
	private Etablissement etablissement;
	private Class<? extends Users> usersClass;
	private CustomWizard userCreationWizard;
	private Runnable onFinishRunnable;
	private Runnable onCancelRunnable;
	private Runnable onStepChangedRunnable;
	
	public UserCreationView() {
	}

	@PostConstruct
	public void postConstruct(){
		CssLayout mainLayout=new CssLayout();
		userCreationWizard = new CustomWizard();
		userCreationWizard.getFooter().addStyleName("footer");
		userCreationWizard.getFooter().setWidth("100%");
		userCreationWizard.getFinishButton().addStyleName("default");
		userCreationWizard.addListener(this);
		buildWizard(userCreationWizard);
		mainLayout.addComponent(userCreationWizard);
		setCompositionRoot(mainLayout);
		
	}

	private void buildWizard(Wizard userCreationWizard) {
		userCreationWizard.addStep(new EtablissementStep());
		userCreationWizard.addStep(new UserTypeStep());
		userCreationWizard.addStep(new UserFormStep());
	}

	private class EtablissementStep implements WizardStep{

		@Override
		public String getCaption() {
			return "Select etablissement";
		}

		@Override
		public com.vaadin.ui.Component getContent() {
			CssLayout cssLayout=new CssLayout();
			final EtablissementComboBox etablissementComboBox = applicationContext
					.getBean(EtablissementComboBox.class);
			etablissementComboBox.setCaption("Select the etablissement");
			cssLayout.addComponent(etablissementComboBox);
			etablissementComboBox.addValueChangeListener(new ValueChangeListener() {
				
				@Override
				public void valueChange(ValueChangeEvent event) {
					if(event!=null && event.getProperty()!=null){
						etablissement=(Etablissement) ((JPAContainerItem)etablissementComboBox.getEtablissementContainer().getItem(event.getProperty().getValue())).getEntity();
					}
					
				}
			});
			if(etablissementComboBox.getValue()!=null){
				etablissement=(Etablissement) ((JPAContainerItem)etablissementComboBox.getEtablissementContainer().getItem(etablissementComboBox.getValue())).getEntity();
			}
			return cssLayout;
		}

		@Override
		public boolean onAdvance() {
			return etablissement!=null;
		}

		@Override
		public boolean onBack() {
			return false;
		}
		
	}
	
	private class UserTypeStep implements WizardStep{

		@Override
		public String getCaption() {
			return "Select user type";
		}

		@Override
		public com.vaadin.ui.Component getContent() {
			CssLayout cssLayout=new CssLayout();
			ComboBox userTypeComboBox=new ComboBox("Select the User type");
			userTypeComboBox.addItem(Etudiant.class);
			userTypeComboBox.addItem(Professeur.class);
			userTypeComboBox.addItem(Parent.class);
			cssLayout.addComponent(userTypeComboBox);
			userTypeComboBox.addValueChangeListener(new ValueChangeListener() {
				@Override
				public void valueChange(ValueChangeEvent event) {
					if(event!=null && event.getProperty()!=null){
						usersClass=(Class) event.getProperty().getValue();
					}
				}
			});
			return cssLayout;
		}

		@Override
		public boolean onAdvance() {
			return usersClass!=null;
		}

		@Override
		public boolean onBack() {
			return true;
		}
	}
	
	private class UserFormStep implements WizardStep{

		private  Form userForm;

		@Override
		public String getCaption() {
			return "Edit user information";
		}

		
		@Override
		public com.vaadin.ui.Component getContent() {
			Users user;
			try {
				user = usersClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			userForm=new Form();
			userForm.setFormFieldFactory(new CustomFieldFactory());
			
			CachingMutableLocalEntityProvider entityProvider = new CachingMutableLocalEntityProvider(usersClass, entityManager);
			jpaContainer = new JPAContainer(usersClass);
			jpaContainer.setEntityProvider(entityProvider);
			HibernateLazyLoadingDelegate hibernateLazyLoadingDelegate = new HibernateLazyLoadingDelegate();
			entityProvider.setLazyLoadingDelegate(hibernateLazyLoadingDelegate);
			beanItem = jpaContainer.createEntityItem(user);
			Object[] updatePropertiesId=AnnotationUtils.getNonJPAPropertiesForCreation(((JPAContainerItem)beanItem).getEntity().getClass()).toArray();
			userForm.setItemDataSource(beanItem,
					updatePropertiesId != null ? Arrays.asList(updatePropertiesId)
							: beanItem.getItemPropertyIds());
			userForm.setValidationVisibleOnCommit(true);
			return userForm;
		}

		@Override
		public boolean onAdvance() {
			return userForm.isValid();
		}

		@Override
		public boolean onBack() {
			return true;
		}
	}

	public Wizard getUserCreationWizard() {
		return userCreationWizard;
	}

	public JPAContainer getJpaContainer() {
		return jpaContainer;
	}

	@Override
	public void activeStepChanged(WizardStepActivationEvent event) {
		if(onStepChangedRunnable!=null){
			onStepChangedRunnable.run();
		}
	}

	@Override
	public void stepSetChanged(WizardStepSetChangedEvent event) {
	}

	@Override
	public void wizardCompleted(WizardCompletedEvent event) {
		jpaContainer.addEntity(beanItem.getEntity());
		if(onFinishRunnable!=null){
			onFinishRunnable.run();
		}
	}

	@Override
	public void wizardCancelled(WizardCancelledEvent event) {
		if(onCancelRunnable!=null){
			onCancelRunnable.run();
		}
	}

	public Runnable getOnFinishRunnable() {
		return onFinishRunnable;
	}

	public void setOnFinishRunnable(Runnable onFinishRunnable) {
		this.onFinishRunnable = onFinishRunnable;
	}

	public Runnable getOnCancelRunnable() {
		return onCancelRunnable;
	}

	public void setOnCancelRunnable(Runnable onCancelRunnable) {
		this.onCancelRunnable = onCancelRunnable;
	}

	public Runnable getOnStepChangedRunnable() {
		return onStepChangedRunnable;
	}

	public void setOnStepChangedRunnable(Runnable onStepChangedRunnable) {
		this.onStepChangedRunnable = onStepChangedRunnable;
	}
	
}
