package com.mobidev;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class EventListActivity extends Activity {
	
	EventDAO eventDAO;
	ImageButton imageButton;
	ListView eventList;
	List<Event> events;
	TextView eventListLabel;
	String email;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_list);
		
		//SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		//String email = sharedPreferences.getString("email", "");
		
		Intent i = getIntent();
		email = i.getStringExtra("emailAdd");
        
		eventDAO = new EventDAO(this);
		
		eventListLabel = (TextView) findViewById(R.id.eventListLabel);
		imageButton = (ImageButton) findViewById(R.id.addImageButton);
		eventList = (ListView) findViewById(R.id.eventList);
		
		events = eventDAO.getEventsOfAUser(eventDAO.getUser(email));
		
		if (events != null && events.size() > 0){
			eventListLabel.setText("Scheduled Events");
			ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(this, R.layout.list_item, events);
			eventList.setAdapter(adapter);
		} else {
			eventListLabel.setText("There are no events scheduled. Please click on the + button to schedule an event.");
		}
		
		imageButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.i("clicks","You clicked Add Event image button");
    	        Intent i = new Intent(EventListActivity.this, ScheduleEventActivity.class);
    	        i.putExtra("emailAdd", email);
    	        startActivity(i);
			}
		});
	}

	
}
