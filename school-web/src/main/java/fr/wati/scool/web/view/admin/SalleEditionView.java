package fr.wati.scool.web.view.admin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.ui.Component;

import fr.wati.school.entities.bean.Salle;
import fr.wati.scool.web.addons.ViewDescription;
import fr.wati.scool.web.components.CRUDPanelFactory;
import fr.wati.scool.web.components.DefaultCRUDPanel;

@SuppressWarnings("serial")
@ViewDescription(name = SalleEditionView.NAME, requiredPermissions = "isAuthenticated()")
public class SalleEditionView extends AbstractAdminView {

	public static final String NAME = "salleEditionView";
	
	@Autowired
	private CRUDPanelFactory crudPanelFactory;
	
	public SalleEditionView() {
	}

	@Override
	public Component getContent() {
		DefaultCRUDPanel<Salle> sallesCrudPanel=crudPanelFactory.getCRUDPanel(Salle.class,"Salles","Edition des salles");
		return sallesCrudPanel;
	}

	@Override
	public String getViewName() {
		return NAME;
	}

}
