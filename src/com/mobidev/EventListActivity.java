package com.mobidev;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class EventListActivity extends Activity {

	private EventDAO eventDAO;
	private SQLiteDatabase dataBase;

	private ArrayList<String> eventID = new ArrayList<String>(); //event id
	private ArrayList<String> eventName = new ArrayList<String>(); //event name
	private ArrayList<String> location = new ArrayList<String>(); //location
	private ArrayList<String> description = new ArrayList<String>();// description

	private ListView eventList;
	private AlertDialog.Builder build;
	String email;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_list);
		
		Intent i = getIntent();
		email = i.getStringExtra("emailAdd");

		eventList = (ListView) findViewById(R.id.List);

		eventDAO = new EventDAO(this);
		
		//click on the add event button
		findViewById(R.id.addImageButton).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(),
						ScheduleEventActivity.class);
    	        i.putExtra("emailAdd", email);
				startActivity(i);

			}
		});
		/**
		//click to update data
		eventList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Intent i = new Intent(getApplicationContext(),
						AddActivity.class);
				i.putExtra("Fname", location.get(arg2));
				i.putExtra("Lname", description.get(arg2));
				i.putExtra("ID", eventID.get(arg2));
				i.putExtra("update", true);
				startActivity(i);

			}
		});**/
	}
	@Override
	protected void onResume() {
		Intent i = getIntent();
		email = i.getStringExtra("emailAdd");
		displayData(eventDAO.getUser(email));
		super.onResume();
	}
	/**
	 * displays data from SQLite
	 */
	private void displayData(User user) {
		dataBase = eventDAO.getWritableDatabase();
		Cursor mCursor = dataBase.rawQuery("SELECT * FROM "
				+ EventDAO.TABLE_EVENTS + " WHERE user_id='" + user.getUserID()  + "'", null);

		eventID.clear();
		eventName.clear();
		location.clear();
		description.clear();
		if (mCursor.moveToFirst()) {
			do {
				eventID.add(mCursor.getString(mCursor.getColumnIndex(EventDAO.KEY_ID)));
				eventName.add(mCursor.getString(mCursor.getColumnIndex(EventDAO.KEY_EVENTNAME)));
				location.add(mCursor.getString(mCursor.getColumnIndex(EventDAO.KEY_LOCATION)));
				description.add(mCursor.getString(mCursor.getColumnIndex(EventDAO.KEY_DESCRIPTION)));

			} while (mCursor.moveToNext());
		}
		DisplayAdapter disadpt = new DisplayAdapter(EventListActivity.this,eventID, eventName, location, description);
		eventList.setAdapter(disadpt);
		mCursor.close();
	}

	

}


/**
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
			eventListLabel.setText("There are no events scheduled. Go on and create your first one!");
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
**/
	

