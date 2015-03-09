package socialeventplaner.view;

import java.util.ArrayList;

import socialeventplaner.view.model.AttendeeAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.socialeventplaner.R;

public class ViewAttendesActivity extends Activity {
private final String LOG_TAG=this.getClass().toString();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.sub_layout_activity_view_attendees);
		ArrayList<String> test = new ArrayList<>();
		for(int i=0;i<=10;i++){
			test.add(String.valueOf(i));
		}
		ListView listView=(ListView) this.findViewById(R.id.view_attendees);
		listView.setAdapter(new AttendeeAdapter(this,0,test));
		listView.setOnItemClickListener(new OnItemClickListener()
		{
		     
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "Click", Toast.LENGTH_LONG).show();
			}
		});
	}
}
