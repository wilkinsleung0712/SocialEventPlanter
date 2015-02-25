package socialeventplaner.view.model;

import java.util.List;




import com.example.socialeventplaner.R;

import socialeventplaner.controller.EditEventController;
import socialeventplaner.model.Event;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class EventAdapter extends ArrayAdapter<Event>{
	private LayoutInflater inflater = (LayoutInflater) this.getContext()
									.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	private Activity activity;
	
	private static class ViewContentHolder{
		 private TextView nameEditText;      
	      private TextView dateText;      
	      private Button editButton;    
	}
	// note that I changed the first param from Context to Activity since
	   // we need an Activity to startActitivyForResult
	public EventAdapter(Activity activity, int textViewResourceId,List<Event> objects) {
		super(activity, textViewResourceId,objects);
		
		this.activity=activity;
	}
	
	public View getView(int position,View inflatedView,ViewGroup parent){
		// get the specific event
		Event event = this.getItem(position);
		 // see android.widget.Adapter.getView() for API details
		if(inflatedView==null){
			 // storing the sub views saves us repeating the findViewById calls
			ViewContentHolder holder = new ViewContentHolder();
			//get the view from single row layout
			inflatedView = inflater.inflate(R.layout.event_list_row, null);
			holder.nameEditText=(TextView) inflatedView.findViewById(R.id.eventText);
			holder.dateText=(TextView) inflatedView.findViewById(R.id.dateText);
			holder.editButton=(Button) inflatedView.findViewById(R.id.editButton);
			
			//store the holder in the view
			inflatedView.setTag(holder);
		}
		
		//get the holder so we can get our previously saved View contents
		ViewContentHolder holder = (ViewContentHolder) inflatedView.getTag();
		
		holder.nameEditText.setText(event.getTitle());
		holder.dateText.setText(java.text.DateFormat.getDateInstance().format(event.getEventDate().getTime()));
		holder.editButton.setOnClickListener(new EditEventController(event.getId(),activity));
		return inflatedView;
	}


	
}