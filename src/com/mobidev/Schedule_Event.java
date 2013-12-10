package com.mobidev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Schedule_Event extends Fragment implements ActionBar.TabListener {
	
	MySQLiteHelper eventDB;
	private Fragment thisFragment;
	EditText eventName;
	EditText location;
	EditText description;
	//EditText email;
	Button scheduleBtn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActivity().setContentView(R.layout.schedule_event);
		
		eventName = (EditText) getActivity().findViewById(R.id.eventName);
		location = (EditText) getActivity().findViewById(R.id.location);
		description = (EditText) getActivity().findViewById(R.id.description);
		//email = (EditText) getActivity().findViewById(R.id.emailAdd);
		scheduleBtn = (Button) getActivity().findViewById(R.id.selectDateBtn);
		
		eventDB = new MySQLiteHelper(getActivity());
		
		scheduleBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Event newEvent = new Event();
				newEvent.setEventName(eventName.getText().toString());
				newEvent.setLocation(location.getText().toString());
				newEvent.setDescription(description.getText().toString());
				//newEvent.setEmail(email.getText().toString());
				
				long success = eventDB.addEvent(newEvent);
				
				if(success > -1)
					Toast.makeText(getActivity().getApplicationContext(), "Event was scheduled!", Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(getActivity().getApplicationContext(), "Something went wrong! Event was not scheduled.", Toast.LENGTH_SHORT).show();
				
				saveSomeValues();
				
			}
		});
	}
	
	//This function saves selections to a file stored in the application's internal storage
	public void saveSomeValues() {
		String eol = System.getProperty("line.separator");
    	BufferedWriter writer = null;
    	
    	try {
    		writer = new BufferedWriter(new OutputStreamWriter(getActivity().openFileOutput("MOBIDEV_events", getActivity().MODE_PRIVATE)));
    		writer.write(eventName.getText().toString() + eol);
    		writer.write(location.getText().toString() + eol);
    		writer.write(description.getText().toString() + eol);
    		//writer.write(email.getText().toString() + eol);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		if (writer != null) {
    			try {
    				writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	}
	}
	
	//This function reads saved selections from a file stored in the application's internal storage
	public void readSomeValues() {
		BufferedReader input = null;
		
    	try {
    		input = new BufferedReader(new InputStreamReader(getActivity().openFileInput("MOBIDEV_events")));
    	    eventName.setText(input.readLine());
    	    location.setText(input.readLine());
    	    description.setText(input.readLine());
    	    //email.setText(input.readLine());
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		thisFragment = new Schedule_Event();
		ft.add(android.R.id.content, thisFragment);
		ft.attach(thisFragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		ft.remove(thisFragment);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}

}
