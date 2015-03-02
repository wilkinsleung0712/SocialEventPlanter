package socialeventplaner.view;

import java.util.Calendar;

import socialeventplaner.controller.CreateNewEventController;
import socialeventplaner.model.Event;
import socialeventplaner.model.EventModel;
import socialeventplaner.model.SimpleEvent;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialeventplaner.R;

public class AddEventActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.sub_layout_activity_create_event);
		//enable the app icon as up button.
				ActionBar actionBar = this.getActionBar();
				actionBar.setDisplayHomeAsUpEnabled(true);
				//end of enable the app icon as up button.
		Button createEventButton = (Button) this.findViewById(R.id.create_event_button);
		
		
		createEventButton.setOnClickListener(new CreateNewEventController("",this));
	}
	
	
	public String getEventName(){
		EditText newEventName=(EditText) this.findViewById(R.id.new_event_name);
		return newEventName.getText().toString();
	}
	
	public Calendar getEventDate(){
		Calendar calendar = Calendar.getInstance();
		DatePicker datePicker = (DatePicker) this.findViewById(R.id.create_event_datePicker);
		calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
		return calendar;
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
		super.finish();
	}

	


}
