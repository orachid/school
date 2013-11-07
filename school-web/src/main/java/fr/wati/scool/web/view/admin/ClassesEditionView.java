package fr.wati.scool.web.view.admin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.ui.Component;

import fr.wati.school.entities.bean.Classe;
import fr.wati.scool.web.addons.ViewDescription;
import fr.wati.scool.web.components.CRUDPanelFactory;
import fr.wati.scool.web.components.DefaultCRUDPanel;

@SuppressWarnings("serial")
@ViewDescription(name = ClassesEditionView.NAME, requiredPermissions = "isAuthenticated()")
public class ClassesEditionView extends AbstractAdminView {

	public static final String NAME = "classesEditionView";
	
	@Autowired
	private CRUDPanelFactory crudPanelFactory;
	
	public ClassesEditionView() {
	}

	@Override
	public Component getContent() {
		DefaultCRUDPanel<Classe> classesCrudPanel=crudPanelFactory.getCRUDPanel(Classe.class,"Classes","Edition des classes");
		return classesCrudPanel;
	}

	@Override
	public String getViewName() {
		return NAME;
	}

}
