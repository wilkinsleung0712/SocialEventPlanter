package socialeventplaner.model;

import java.util.ArrayList;
import java.util.Calendar;

public interface Event {

	public abstract String getId();

	public abstract String getTitle();

	public abstract Calendar getEventDate();

	public abstract String getVenue();

	public abstract String getNote();

	public abstract ArrayList<String> getAttendees();
	
	public abstract void setTitle(String title);
	public abstract void setEventDate(Calendar eventDate);

}