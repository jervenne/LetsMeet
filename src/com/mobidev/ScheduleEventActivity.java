package com.mobidev;

import com.mobidev.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ScheduleEventActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to schedule_event.xml
        setContentView(R.layout.schedule_event);
        
       Button selectDateBtn = (Button) findViewById(R.id.selectDateBtn);
        
        // Listening to Select Date Button
       selectDateBtn.setOnClickListener(new View.OnClickListener() {
    	   public void onClick(View v) {
    	    	Log.i("clicks","You clicked Select Date button");
    	        Intent i = new Intent(ScheduleEventActivity.this, SelectDateActivity.class);
    	        startActivity(i);
    	    }
       });
    }		
    
}
