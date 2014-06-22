/**
 * 
 */
package fr.wati.scool.web.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.data.Container.Indexed;
import com.vaadin.ui.components.calendar.event.CalendarEvent;

import fr.wati.school.entities.bean.ConseilleDeClasse;
import fr.wati.school.entities.bean.Cours;
import fr.wati.school.entities.bean.Evenement;
import fr.wati.school.entities.bean.Reunion;
import fr.wati.school.entities.bean.Users;

/**
 * @author Rachid Ouattara
 *
 */
@SuppressWarnings("serial")
public class EvenementContainerEventProvider extends CustomContainerEventProvider {

	/**
	 * @param container
	 */
	public EvenementContainerEventProvider(Indexed container) {
		super(container);
		setStartDateProperty("dateDebut");
		setEndDateProperty("dateFin");
	}

	/* (non-Javadoc)
	 * @see com.vaadin.ui.components.calendar.ContainerEventProvider#getEvents(java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CalendarEvent> getEvents(Date startDate, Date endDate) {
		List<CalendarEvent> events = super.getEvents(startDate, endDate);
		Iterator<CalendarEvent> iterator=events.iterator();
		while (iterator.hasNext()) {
			ContainerCalendarEvent calendarEvent = (ContainerCalendarEvent) iterator.next();
			
			JPAContainerItem<Evenement> item = (JPAContainerItem<Evenement>) getContainerDataSource().getItem(getContainerDataSource().getIdByIndex(calendarEvent.getContainerIndex()));
			Evenement evenement=item.getEntity();
			StringBuffer captionStringBuffer=new StringBuffer();
			StringBuffer descriptionStringBuffer=new StringBuffer();
			//Compute styleName
			if(evenement.getSalle()!=null){
				descriptionStringBuffer.append("Salle: ");
				descriptionStringBuffer.append(evenement.getSalle().getCode());
			}else {
				descriptionStringBuffer.append("Salle indéfinie");
			}
			if(evenement.getClass()==Reunion.class){
				calendarEvent.setStyleName("reunion");
				captionStringBuffer.append("Reunion: ");
				captionStringBuffer.append(evenement.getNom());
				descriptionStringBuffer.append(evenement.getCommentaire());
			}else if (evenement.getClass()==Evenement.class) {
				calendarEvent.setStyleName("evenement");
				captionStringBuffer.append("Evenement: ");
				captionStringBuffer.append(evenement.getNom());
				descriptionStringBuffer.append(evenement.getCommentaire());
			}else if (evenement.getClass()==ConseilleDeClasse.class) {
				calendarEvent.setStyleName("conseille");
				captionStringBuffer.append("Conseille de classe: ");
				captionStringBuffer.append(evenement.getNom());
				descriptionStringBuffer.append(evenement.getCommentaire());
			}else if (evenement.getClass()==Cours.class) {
				calendarEvent.setStyleName("cours");
				if(((Cours)evenement).getMatiere()!=null){
					captionStringBuffer.append("Cours: ");
					captionStringBuffer.append(((Cours)evenement).getMatiere().getCode());
				}else {
					captionStringBuffer.append("Cours indéfinie");
				}
				if(((Cours)evenement).getProfesseur()!=null){
					descriptionStringBuffer.append("\n");
					descriptionStringBuffer.append(((Cours)evenement).getProfesseur().getNom());
				}else {
					descriptionStringBuffer.append("Prof inconue");
				}
				
				
			}
			calendarEvent.setCaption(captionStringBuffer.toString());
			calendarEvent.setDescription(descriptionStringBuffer.toString());
		}
		return events;
	}

	public static void main(String[] args) {
		List<Users> users=new ArrayList<>();
		users.add(new Users());
		users.add(new Users());
		List<Users> users2=Collections.unmodifiableList(users);
		users2.get(0).setEnabled(true);
		System.out.println(users2.get(0).isEnabled());
		users2.remove(0);
	}
}
