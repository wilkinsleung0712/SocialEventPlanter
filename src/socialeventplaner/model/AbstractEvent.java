package socialeventplaner.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public abstract class AbstractEvent implements Event {
	private String id;
	private String title;
	// a long would be more efficient but requires more Calendar manipulation
	private Calendar eventDate;
	private String venue;
	private String note;
	private ArrayList<String> listOfAttendees;
	
	public AbstractEvent() {
		super();
		// TODO Auto-generated constructor stub
		id = UUID.randomUUID().toString();
		listOfAttendees = new ArrayList<>();
	}
	
	public AbstractEvent(String title, Calendar eventDate) {
		this();
		this.title = title;
		this.eventDate = eventDate;
	}

	/* (non-Javadoc)
	 * @see socialeventplaner.model.Event#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see socialeventplaner.model.Event#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/* (non-Javadoc)
	 * @see socialeventplaner.model.Event#getEventDate()
	 */
	@Override
	public Calendar getEventDate() {
		return eventDate;
	}

	/* (non-Javadoc)
	 * @see socialeventplaner.model.Event#getVenue()
	 */
	@Override
	public String getVenue() {
		return venue;
	}

	/* (non-Javadoc)
	 * @see socialeventplaner.model.Event#getNote()
	 */
	@Override
	public String getNote() {
		return note;
	}

	/* (non-Javadoc)
	 * @see socialeventplaner.model.Event#getAttendees()
	 */
	@Override
	public ArrayList<String> getAttendees() {
		return listOfAttendees;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setEventDate(Calendar eventDate) {
		this.eventDate = eventDate;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setAttendees(ArrayList<String> listOfAttendees) {
		this.listOfAttendees = listOfAttendees;
	}

	@Override
	public String toString() {
		return "AbstractEvent [id=" + id + ", title=" + title + ", eventDate="
				+ eventDate + ", venue=" + venue + ", note=" + note
				+ ", attendees=" + listOfAttendees + "]";
	}



	
	public void addAttendees(String attendee){
		if (!listOfAttendees.contains(attendee)) {
			listOfAttendees.add(attendee);
		}
	}
	
	public void removeAttendees(String attendee){
		if(listOfAttendees.contains(attendee)){
			listOfAttendees.remove(attendee);
		}
		
	}
	
	public int getNumberOfAttendees(){
		return this.listOfAttendees.size();
	}
	
	
}
