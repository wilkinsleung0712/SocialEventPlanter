package socialeventplaner.view.model;

import java.util.List;


import com.example.socialeventplaner.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AttendeeAdapter extends ArrayAdapter<String> {
	private LayoutInflater inflater = (LayoutInflater) this.getContext()
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	private Activity activity;
	
	private static class ViewContentHolder{
		private TextView firstLineText;
		private TextView secondLineText;
	}
	public AttendeeAdapter(Activity activity, int resource, List<String> objects) {
		super(activity, resource, objects);
		// TODO Auto-generated constructor stub
		this.activity=activity;
		
		
	}
	
	public View getView(int position,View inflatedView,ViewGroup parent){
		String s = this.getItem(position);
		if(inflatedView ==null){
			// storing the sub views saves us repeating the findViewById calls
			ViewContentHolder holder = new ViewContentHolder();
			//get the view from single row layout
			inflatedView=inflater.inflate(R.layout.attendees_list_row, null);
			holder.firstLineText=(TextView) inflatedView.findViewById(R.id.firstLine);
			holder.secondLineText=(TextView) inflatedView.findViewById(R.id.secondLine);
			inflatedView.setTag(holder);
		}
		//get the holder so we can get our previously saved View contents
		ViewContentHolder holder = (ViewContentHolder) inflatedView.getTag();
		holder.firstLineText.setText(s);		
		return inflatedView;
		
	}
	
	
	

	
}
