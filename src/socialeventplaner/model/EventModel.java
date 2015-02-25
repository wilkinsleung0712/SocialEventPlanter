package socialeventplaner.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import android.util.Log;
//CRUD for event controller
public class EventModel {
	private Map<String,Event> idMap = new HashMap<>();
	private SortedMap<Calendar,Event> sortedMap = new TreeMap<>();
	private final String LOG_CAT = this.getClass().getName();
	 public static final String EVENT_ID_EXTRA = "eventIdExtra";
	private static EventModel singletonInstance;
	
	private EventModel(){
		for(int i=10;i>=1;i--){
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH,i);
			Event event = new SimpleEvent("Event "+i,calendar);
			addEvent(event);
		}
	}
	
	
	public void addEvent(Event event){
		this.sortedMap.put(event.getEventDate(), event);
		this.idMap.put(event.getId(), event);
	}
	
	public boolean deleteEvent(Event event){
		 return this.idMap.remove(event.getId())!=null
				 &&this.sortedMap.remove(event.getEventDate())!=null;
	}
	
	public void editEvent(Calendar oldDate,Event newEvent){
		sortedMap.remove(oldDate);
	    sortedMap.put(newEvent.getEventDate(), newEvent);
	}
	
	public static EventModel getSingletonInstance(){
		if(singletonInstance==null){
			singletonInstance=new EventModel();
		}
		return singletonInstance;
	}
	public Event getEventforId(String Id){
		return idMap.get(Id);
	}
	
	public List<Event> getEventList(){
		
		return new ArrayList<Event>(sortedMap.values());
	}
	
}
