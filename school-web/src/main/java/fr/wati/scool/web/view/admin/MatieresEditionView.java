package fr.wati.scool.web.view.admin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.ui.Component;

import fr.wati.school.entities.bean.Matiere;
import fr.wati.scool.web.addons.ViewDescription;
import fr.wati.scool.web.components.CRUDPanelFactory;
import fr.wati.scool.web.components.DefaultCRUDPanel;

@SuppressWarnings("serial")
@ViewDescription(name = MatieresEditionView.NAME, requiredPermissions = "isAuthenticated()")
public class MatieresEditionView extends AbstractAdminView {
	
	public static final String NAME = "matieresEditionView";
	
	@Autowired
	private CRUDPanelFactory crudPanelFactory;
	public MatieresEditionView() {
	}

	@Override
	public Component getContent() {
		DefaultCRUDPanel<Matiere> matieresCrudPanel=crudPanelFactory.getCRUDPanel(Matiere.class,"Matieres","Edition des matiers");
//		matieresCrudPanel.setVisibleFormProperties("nom","code","coefficient");
//		matieresCrudPanel.setVisibleTableProperties("nom","code","coefficient");
		return matieresCrudPanel;
	}

	@Override
	public String getViewName() {
		return NAME;
	}

}
