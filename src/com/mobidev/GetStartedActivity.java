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
	
	String emailET = "";
	EventDAO eventDAO;
	String email = "";
	List<User> userList;
	boolean hasUser;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to start_page.xml
        setContentView(R.layout.start_page);
        
        eventDAO = new EventDAO(this);
        
       Button startBtn = (Button) findViewById(R.id.startButton);
       EditText emailAddET = (EditText) findViewById(R.id.emailAdd);
       emailET = emailAddET.getText().toString().trim();
        
        // Listening to Start Button
       startBtn.setOnClickListener(new View.OnClickListener() {
    	   public void onClick(View v) {
    		   
    		   hasUser = false;
    		   Log.d("button", "aaaaaaa");
    		   userList = eventDAO.getAllUsers();
    		   Log.d("button", "bbbbbbb");
    		   
    		   if (userList.size() > 0){
    			   for(User u: userList) {
        			   if (u.getEmail().equals(emailET)) {
        				   hasUser = true;
        				   Log.d("user", emailET);
        				   Toast.makeText(GetStartedActivity.this, "Welcome back!", Toast.LENGTH_LONG).show();
        			   }
        		   }
    		   }
    		   
    		   if (hasUser == false) {
    			   User user = new User();
    			   user.setEmail(emailET);
    			   eventDAO.addUser(user);
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
