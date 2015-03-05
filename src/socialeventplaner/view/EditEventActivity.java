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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialeventplaner.R;



public class EditEventActivity extends Activity {
private Event currentEvent;
private Calendar oldDate;
private Activity activity;
public static final int DATE_CHANGED = 1;
private  final int DIALOG_YES_NO_CONFIRM_DELETE_MESSAGE = 2;
private final int DIALOG_YES_NO_CONFIRM_SAVE_MESSAGE=3;
private final String LOG_TAG = this.getClass().getName();



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.sub_layout_activity_edit_event);
		this.activity=this;
		//enable the app icon as up button.
				ActionBar actionBar = this.getActionBar();
				actionBar.setDisplayHomeAsUpEnabled(true);
				//end of enable the app icon as up button.
		//set current event attribute to the view
		String eventId = this.getIntent().getStringExtra(EventModel.EVENT_ID_EXTRA);
		this.currentEvent=EventModel.getSingletonInstance().getEventforId(eventId);
		
		//get date
		oldDate = this.currentEvent.getEventDate();
		//connect to the view and set the values on the component.
		EditText namedEditText = (EditText) this.findViewById(R.id.new_event_name);
		namedEditText.setText(currentEvent.getTitle());
		
		DatePicker datePicker = (DatePicker) this.findViewById(R.id.create_event_datePicker);
		setDateInDatePicker(datePicker, currentEvent.getEventDate());
	    setDateButtonFromDate(currentEvent.getEventDate());
	    
	      
		
	}

	
	private void setDateButtonFromDate(Calendar eventDate) {
		// TODO Auto-generated method stub
		Button dateButton = (Button) this.findViewById(R.id.create_event_button);
		dateButton.setText(java.text.DateFormat.getDateInstance().format(eventDate.getTime()));
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		 // would normally do this in onPause() but cannot setResult() for calling
	      // activity there see https://code.google.com/p/android/issues/detail?id=1671
	      saveData();
	      Log.i(LOG_TAG,"onfinish()");
		super.finish();
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(LOG_TAG,"onPause()");
	}
	private void saveData() {
		// TODO Auto-generated method stub
		EditText namedEditText = (EditText) this.findViewById(R.id.new_event_name);
		this.currentEvent.setTitle(namedEditText.getText().toString());
		//set date in model from data picker
		DatePicker datePicker = (DatePicker) this.findViewById(R.id.create_event_datePicker);
		this.currentEvent.setEventDate(this.getdateFromDatePicker(datePicker));
		// if we change event date then we change model sort order!
		if(!compareDate(oldDate,currentEvent.getEventDate())){
			EventModel.getSingletonInstance().editEvent(oldDate, currentEvent);
			Toast.makeText(this, R.string.toastDateMsg, Toast.LENGTH_LONG).show();
			//notify ListActivity to resort
			this.setResult(EditEventActivity.DATE_CHANGED);
		}
		
	}
	private Calendar getdateFromDatePicker(DatePicker datePicker) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
		return calendar;
	}
	
private void setDateInDatePicker(DatePicker datePicker, Calendar calendar) {
		
		datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
				//auto react when the date picker has changed. 		
				new DatePicker.OnDateChangedListener() {
							
							@Override
							public void onDateChanged(DatePicker view, int year, int monthOfYear,
									int dayOfMonth) {
								//set the current selected date on the button to display
								setDateButtonFromDate(getDateFromFields(year, monthOfYear,
				                        dayOfMonth));
							}
						});
	}

protected Calendar getDateFromFields(int year, int monthOfYear,
		int dayOfMonth) {
	// TODO Auto-generated method stub
	Calendar calendar = Calendar.getInstance();
	calendar.set(year, monthOfYear, dayOfMonth);
	return calendar;
}
	

//compare the old date and new current date inorder to resort the map.
//if return the boolean to see it the date has been changed.
	private boolean compareDate(Calendar date1,Calendar date2){
		return date1.get(Calendar.DAY_OF_MONTH) == (date2
	            .get(Calendar.DAY_OF_MONTH))
	            && date1.get(Calendar.MONTH) == (date2.get(Calendar.MONTH))
	            && date1.get(Calendar.MONTH) == (date2.get(Calendar.MONTH));
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = this.getMenuInflater();
		inflater.inflate(R.menu.menubar_view_single_event, menu);
		
		//menu.findItem(R.id.view_save_event).setCheckable(false);
		//menu.findItem(R.id.view_delete_event);
		
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//Match
		switch(item.getItemId()){
		case R.id.view_delete_event:
			this.showDialog(DIALOG_YES_NO_CONFIRM_DELETE_MESSAGE);
			return true;
		case R.id.view_save_event:
			this.showDialog(DIALOG_YES_NO_CONFIRM_SAVE_MESSAGE);
			return true;
			
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		if(id==this.DIALOG_YES_NO_CONFIRM_DELETE_MESSAGE){
			return new AlertDialog.Builder(EditEventActivity.this)
						.setIcon(R.drawable.alert_dialog_icon)
						.setTitle("Warnning")
						.setMessage("Are you sure to delete?")
						.setPositiveButton("YES", 
								new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										EventModel.getSingletonInstance().deleteEvent(currentEvent);
										Intent myIntent = new Intent(activity,EventListActivity.class);
										Toast.makeText(EditEventActivity.this, R.string.toastDeleteEventMsg, Toast.LENGTH_LONG).show();
										activity.startActivityForResult(myIntent, 0);
									}
								})
						.setNegativeButton("NO",
								new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										
									}
								}).create();
						
		}else if(id==this.DIALOG_YES_NO_CONFIRM_SAVE_MESSAGE){
			return new AlertDialog.Builder(EditEventActivity.this)
							.setIcon(R.drawable.alert_dialog_icon)
							.setTitle("Warnning")
							.setMessage("Are you sure to save?")
							.setPositiveButton("YES", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									saveData();
								}
							})
							.setNegativeButton("NO", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									
								}
							})
							.create();
		}
		return super.onCreateDialog(id);
	}
	
	
}
