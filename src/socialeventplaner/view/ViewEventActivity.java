package socialeventplaner.view;

import java.util.Calendar;

import socialeventplaner.model.Event;
import socialeventplaner.model.EventModel;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.socialeventplaner.R;

public class ViewEventActivity extends Activity {
	//private  final int DIALOG_YES_NO_CONFIRM_MESSAGE = 2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.sub_layout_activity_view_event);
		//enable the app icon as up button.
		ActionBar actionBar = this.getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		//end of enable the app icon as up button.
		String eventId = this.getIntent() //get the passed intent for value
						.getStringExtra(EventModel.EVENT_ID_EXTRA);//get the stored value
		Event currentEvent = EventModel.getSingletonInstance().getEventforId(eventId);
		TextView textview = (TextView) this.findViewById(R.id.view_event_name);
		CalendarView calendarView = (CalendarView) this.findViewById(R.id.view_event_calendarView);
		
		textview.setText(currentEvent.getTitle());
		setDateInDatePicker(calendarView,currentEvent.getEventDate());
	}
	
	private void setDateInDatePicker(CalendarView calendarView,Calendar calendar){
		//puting the calendar saved date and time to the datePicker for display.
		//we need to init the datePicker first
		calendarView.setDate(calendar.getTimeInMillis());
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = this.getMenuInflater();
		inflater.inflate(R.menu.menubar_view_single_event, menu);
		menu.findItem(R.id.view_delete_event).setIntent(new Intent(this,AddEventActivity.class));
		return true;
	}
/*
	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//Match
		switch(item.getItemId()){
		case R.id.view_delete_event:
			this.showDialog(DIALOG_YES_NO_CONFIRM_MESSAGE);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		if(id==this.DIALOG_YES_NO_CONFIRM_MESSAGE){
			return new AlertDialog.Builder(ViewEventActivity.this)
						.setIcon(R.drawable.alert_dialog_icon)
						.setTitle("Warnning")
						.setMessage("Are you sure to delete?")
						.setPositiveButton("YES", 
								new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										
									}
								})
						.setNegativeButton("NO",
								new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										
									}
								}).create();
						
		}
		return super.onCreateDialog(id);
	}
	
	*/
	
	
	

}
