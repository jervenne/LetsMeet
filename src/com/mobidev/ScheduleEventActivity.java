package com.mobidev;

import com.mobidev.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ScheduleEventActivity extends Activity{
	
	public static final String PREFS_NAME = "MySavedValues";
	String email, location, description, eventName;
	EditText eventNameET, locationET, descriptionET;
	Button selectDateBtn;
	EventDAO eventDAO;
	int eventID;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       // Set View to schedule_event.xml
       setContentView(R.layout.schedule_event);
       
       eventDAO = new EventDAO(this);
       
       Intent i = getIntent();
       email = i.getStringExtra("emailAdd");
       Log.i("intentEmail", email);
       
       selectDateBtn = (Button) findViewById(R.id.selectDateBtn);
       eventNameET = (EditText) findViewById(R.id.eventName);
       locationET = (EditText) findViewById(R.id.location);
       descriptionET = (EditText) findViewById(R.id.description);
       
        // Listening to Select Date Button
       selectDateBtn.setOnClickListener(new View.OnClickListener() {
    	   public void onClick(View v) {
    		   eventName = eventNameET.getText().toString().trim();
    		   location = locationET.getText().toString().trim();
    		   description = descriptionET.getText().toString().trim();
    		   
    		   if (eventName.length() == 0){
    			   Toast.makeText(ScheduleEventActivity.this, "You did not enter an event name", Toast.LENGTH_SHORT).show();
    			   return;
    			   
    		   } else {
    			   
    			   eventID = eventDAO.getLatestEventID() + 1;
    			   Log.i("latestEventID", String.valueOf(eventID));
    			   Event e = new Event(eventID, eventName, location, description);
    			   eventDAO.addEvent(e, eventDAO.getUser(email));
    			   
    			   Log.i("clicks","You clicked Select Date button");
        	       Intent i = new Intent(ScheduleEventActivity.this, SelectDateTimeActivity.class);
        	       i.putExtra("emailAdd", email);
        	       i.putExtra("eventID", eventID);
        	       
        	       if (location.length() > 0) {
        	    	   i.putExtra("location", location);
        	       }
        	       
        	       if (description.length() > 0) {
        	    	   i.putExtra("description", description);
        	       }
        	       
        	       startActivity(i);
    		   }
    		   
    	    }
       });
    }		
    
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		SharedPreferences savedValues = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		SharedPreferences.Editor editor = savedValues.edit();
		editor.putString("eventName", eventName);
		
		editor.commit();
		
	}
	
}
