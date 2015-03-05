package socialeventplaner.controller;

import socialeventplaner.model.EventModel;
import socialeventplaner.view.EditEventActivity;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Toast;

public class OnLongPressController implements OnLongClickListener{
	
	private Activity activity;
	private String eventId;

	public OnLongPressController(Activity activity,String eventId) {
		// TODO Auto-generated constructor stub
		super();
		this.activity = activity;
		this.eventId=eventId;
	}


	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		Intent myIntent = new Intent(activity,EditEventActivity.class);
		// put the id as an extra so the EditEventActivity knows which event to edit
		myIntent.putExtra(EventModel.EVENT_ID_EXTRA, eventId);
		
		Toast.makeText(activity, "long press",  Toast.LENGTH_LONG).show();
		activity.startActivityForResult(myIntent, 0);
		return false;
	}

}
