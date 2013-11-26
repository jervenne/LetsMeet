package com.mobidev;

import com.mobidev.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class GetStartedActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to start_page.xml
        setContentView(R.layout.start_page);
        
       Button startBtn = (Button) findViewById(R.id.startButton);
        
        // Listening to Start Button
       startBtn.setOnClickListener(new View.OnClickListener() {
    	   public void onClick(View v) {
    	    	Log.i("clicks","You clicked Get Started button");
    	        Intent i = new Intent(GetStartedActivity.this, ScheduleEventActivity.class);
    	        startActivity(i);
    	    }
       });
    }		
}
