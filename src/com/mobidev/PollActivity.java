package com.mobidev;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PollActivity extends Activity {
	
	public static final String PREFS_NAME = "MySavedValues";
	String eventName = "";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll);
        
       Button saveBtn = (Button) findViewById(R.id.saveButton);
       TextView eventNameTV = (TextView) findViewById(R.id.eventName);
       
       SharedPreferences savedValues = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
       eventName = savedValues.getString("eventName", "");
       eventNameTV.setText("" + eventName);

       saveBtn.setOnClickListener(new View.OnClickListener() {
    	   public void onClick(View v) {
    	    	Log.i("clicks","You clicked Save button");
    	        //Intent i = new Intent(PollActivity.this, SelectDateTimeActivity.class);
    	        //startActivity(i);
    	    }
       });
    }		

}
