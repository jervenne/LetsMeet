package com.mobidev;

import java.util.List;

import com.mobidev.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GetStartedActivity extends Activity{
	
	String emailStr = "";
	EventDAO eventDAO;
	String email = "";
	boolean hasUser;
	EditText emailAddET;
	Button startBtn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to start_page.xml
        setContentView(R.layout.start_page);
        
        eventDAO = new EventDAO(this);
        
       startBtn = (Button) findViewById(R.id.startButton);
       emailAddET = (EditText) findViewById(R.id.emailAdd);
        
        // Listening to Start Button
       startBtn.setOnClickListener(new View.OnClickListener() {
    	   public void onClick(View v) {
    		   
    		   emailStr = emailAddET.getText().toString().trim();
    		   Log.d("emailEditText", emailStr);
    		   hasUser = false;
    		   Log.d("eventDAO", "going to eventDAO");
    		   User user = eventDAO.getUser(emailStr);
    		   Log.d("eventDAO", "exiting eventDAO");
    		   
    		   if (user != null) {
    			   Log.d("if", "entering if statement");
				   hasUser = true;			   
				   Toast.makeText(GetStartedActivity.this, "Welcome back, " + emailStr, Toast.LENGTH_LONG).show();
    		   } else {

    			   Log.d("if", "entering if statement2");
    			   User u = new User();
    			   u.setEmail(emailStr);
    			   eventDAO.addUser(u);
    			   Toast.makeText(GetStartedActivity.this, "A user account with your email address has been created!", Toast.LENGTH_LONG).show();
    		   
    		   }
    		   
    	    	Log.i("clicks","You clicked Get Started button");
    	        Intent i = new Intent(GetStartedActivity.this, Event_Main.class);
    	        //Intent i = new Intent(GetStartedActivity.this, ScheduleEventActivity.class);
    	        startActivity(i);
    	    }
       });
    }		
}
