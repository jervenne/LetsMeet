package com.mobidev;

import com.mobidev.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;

public class ScheduleEventActivity extends Activity{
	
	public static final String PREFS_NAME = "MySavedValues";
	String eventName = "";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to schedule_event.xml
        setContentView(R.layout.schedule_event);
        
       Button selectDateBtn = (Button) findViewById(R.id.selectDateBtn);
       
       EditText eventNameET = (EditText) findViewById(R.id.eventName);
       eventName = eventNameET.getText().toString();
       
        // Listening to Select Date Button
       selectDateBtn.setOnClickListener(new View.OnClickListener() {
    	   public void onClick(View v) {
    	    	Log.i("clicks","You clicked Select Date button");
    	        Intent i = new Intent(ScheduleEventActivity.this, SelectDateTimeActivity.class);
    	        startActivity(i);
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
