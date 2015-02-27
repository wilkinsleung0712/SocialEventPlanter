package socialeventplaner.controller;

import socialeventplaner.model.EventModel;
import socialeventplaner.view.ViewEventActivity;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class ViewEventController implements OnClickListener{
private String eventId;
private Activity activity;
	public ViewEventController(String eventId, Activity activity) {
		// TODO Auto-generated constructor stub
		this.eventId=eventId;
		this.activity=activity;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent myIntent = new Intent(activity,ViewEventActivity.class);
		myIntent.putExtra(EventModel.EVENT_ID_EXTRA, eventId);
		activity.startActivityForResult(myIntent, 0);
		
	}

}
