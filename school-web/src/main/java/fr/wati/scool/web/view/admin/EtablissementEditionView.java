/**
 * 
 */
package fr.wati.scool.web.view.admin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.ui.Component;

import fr.wati.school.entities.bean.Etablissement;
import fr.wati.scool.web.addons.ViewDescription;
import fr.wati.scool.web.components.CRUDPanelFactory;
import fr.wati.scool.web.components.DefaultCRUDPanel;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
@ViewDescription(name = EtablissementEditionView.NAME, requiredPermissions = "isAuthenticated()")
public class EtablissementEditionView extends AbstractAdminView {

	public static final String NAME = "etablissementEditionView";
	
	@Autowired
	private CRUDPanelFactory crudPanelFactory;
	
	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.admin.AbstractAdminView#getContent()
	 */
	@Override
	public Component getContent() {
		DefaultCRUDPanel<Etablissement> classesCrudPanel=crudPanelFactory.getCRUDPanel(Etablissement.class,"Etablissement","Edition des etablissement");
		return classesCrudPanel;
	}

	/* (non-Javadoc)
	 * @see fr.wati.scool.web.view.AbstractView#getViewName()
	 */
	@Override
	public String getViewName() {
		return NAME;
	}

}
