package socialeventplaner.view;

import socialeventplaner.model.EventModel;
import socialeventplaner.view.model.EventAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.socialeventplaner.R;

public class EventListActivity extends ListActivity{
private EventAdapter eventAdapter;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//SET THE VIEW TO DISPLAY THIS ACTIVITY
		this.setContentView(R.layout.main_layout);
		eventAdapter = new EventAdapter(this,0,EventModel.getSingletonInstance().getEventList());
		this.setListAdapter(eventAdapter);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// check to see if Model has been re-ordered and we need to reset the adapter
		if(resultCode == EditEventActivity.DATE_CHANGED){
			eventAdapter.clear();
			eventAdapter.addAll(EventModel.getSingletonInstance().getEventList());
		}
		eventAdapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = this.getMenuInflater();
		inflater.inflate(R.menu.menubar, menu);
		menu.findItem(R.id.create_new).setIntent(new Intent(this,AddEventActivity.class));
		return true;
	}
	
}
