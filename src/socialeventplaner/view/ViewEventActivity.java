package socialeventplaner.view;

import java.util.Calendar;

import socialeventplaner.model.Event;
import socialeventplaner.model.EventModel;
import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.socialeventplaner.R;

public class ViewEventActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.sub_layout_activity_view_event);
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

}
