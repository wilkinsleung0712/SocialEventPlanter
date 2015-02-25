package socialeventplaner.controller;

import socialeventplaner.model.EventModel;
import socialeventplaner.view.EditEventActivity;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class EditEventController implements OnClickListener{

	private String eventId;
	private Activity activity;
	public EditEventController(String eventId,Activity activity){
		this.eventId=eventId;
		this.activity=activity;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent myIntent = new Intent(activity,EditEventActivity.class);
		// put the id as an extra so the EditEventActivity knows which event to edit
		myIntent.putExtra(EventModel.EVENT_ID_EXTRA, eventId);
		activity.startActivityForResult(myIntent, 0);
	}

}
