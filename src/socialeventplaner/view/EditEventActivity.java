package socialeventplaner.view;

import java.util.Calendar;

import socialeventplaner.model.Event;
import socialeventplaner.model.EventModel;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialeventplaner.R;



public class EditEventActivity extends Activity {
private Event currentEvent;
private Calendar oldDate;
public static final int DATE_CHANGED = 1;
private final String LOG_TAG = this.getClass().getName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.sub_layout_activity_edit_event);
		
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
	
}
