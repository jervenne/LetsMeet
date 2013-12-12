package com.mobidev;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class PollActivity extends Activity {
	
	public static final String PREFS_NAME = "MySavedValues";
	String email, eventName;
	int eventID;
	TableLayout table;
	Button saveBtn;
	TextView eventNameTV;
	EventDAO eventDAO;
	Event event;
	ArrayList<User> respondents = new ArrayList<User>();
	ArrayList<Option> timeslots = new ArrayList<Option>();
	Button button;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.poll);
        
       //saveBtn = (Button) findViewById(R.id.saveButton);
       eventNameTV = (TextView) findViewById(R.id.eventName);
       //table = (TableLayout) findViewById(R.id.table);
       button = (Button) findViewById(R.id.button1);
       
       Intent i = getIntent();
	   email = i.getStringExtra("emailAdd");
	   eventID = i.getIntExtra("eventID", 0);
	   
       //eventDAO = new EventDAO(this);
       //event = eventDAO.getEvent(eventID);
       //Log.i("pollactivity", "entering getRespondents");
       //respondents = eventDAO.getRespondents(event);
       // timeslots = event.getOptions();
       
       SharedPreferences savedValues = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
       eventName = savedValues.getString("eventName", "");
       eventNameTV.setText("" + eventName);
       
       /*
       TableRow tr_head = new TableRow(this);
       tr_head.setId(10);
       tr_head.setBackgroundColor(Color.GRAY);
       tr_head.setLayoutParams(new LayoutParams(
    		   LayoutParams.MATCH_PARENT,
    		   LayoutParams.WRAP_CONTENT));
       
       TextView label_timeslot = new TextView(this);
       label_timeslot.setId(20);
       label_timeslot.setText("DATE");
       label_timeslot.setTextColor(Color.WHITE);
       label_timeslot.setPadding(5, 5, 5, 5);
       tr_head.addView(label_timeslot);// add the column to the table row here

       TextView label_weight_kg = new TextView(this);
       label_weight_kg.setId(21);// define id that must be unique
       label_weight_kg.setText("Wt(Kg.)"); // set the text for the header 
       label_weight_kg.setTextColor(Color.WHITE); // set the color
       label_weight_kg.setPadding(5, 5, 5, 5); // set the padding (if required)
       tr_head.addView(label_weight_kg); // add the column to the table row here
       */
       
       button.setOnClickListener(new View.OnClickListener() {
    	   public void onClick(View v) {
    	    	Log.i("clicks","You clicked Save button");
    	        Intent i = new Intent(PollActivity.this, EventListActivity.class);
    	        i.putExtra("emailAdd", email);
	    	    i.putExtra("eventID", eventID);
    	        startActivity(i);
    	    }
       });
    }		

}
