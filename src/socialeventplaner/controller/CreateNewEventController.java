package socialeventplaner.controller;

import java.util.Calendar;

import com.example.socialeventplaner.R;

import socialeventplaner.model.Event;
import socialeventplaner.model.EventModel;
import socialeventplaner.model.SimpleEvent;
import socialeventplaner.view.AddEventActivity;
import socialeventplaner.view.EditEventActivity;
import socialeventplaner.view.EventListActivity;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class CreateNewEventController implements OnClickListener {
	private String eventId;
	private AddEventActivity activity;
	
	
	public CreateNewEventController(String eventId, AddEventActivity activity) {
		super();
		this.eventId = eventId;
		this.activity = activity;

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//calling to save the information
		String eventName=activity.getEventName();
		Calendar eventDate = activity.getEventDate();
		Event newEvent = new SimpleEvent(eventName,eventDate);
		EventModel.getSingletonInstance().addEvent(newEvent);
		//set intent to change view to EventListActivity
		Intent intent = new Intent(activity,EventListActivity.class);
		//use toast to notice user.
		Toast.makeText(activity, R.string.toastSaveMsg, Toast.LENGTH_LONG).show();
		//start the activity as well as notify the activity
		activity.startActivityForResult(intent, EditEventActivity.DATE_CHANGED);
	}

}
