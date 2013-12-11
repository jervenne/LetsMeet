package com.mobidev;

import java.util.ArrayList;

import com.mobidev.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendInvitationActivity extends Activity {
	EditText emailAddET;
	Button sendBtn;
	EventDAO eventDAO;
	Event event;
	String[] emailArray;
	String email, emailAddresses;
	boolean isValid;
	int eventID;
	ArrayList<User> respondents;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       // Set View to send_invitation.xml
       setContentView(R.layout.send_invitation);
       
       Intent i = getIntent();
	   email = i.getStringExtra("emailAdd");
	   eventID = i.getIntExtra("eventID", 0);
	   
       eventDAO = new EventDAO(this);
       event = eventDAO.getEvent(eventID);
       
       sendBtn = (Button) findViewById(R.id.sendButton);
       emailAddET = (EditText) findViewById(R.id.emailAdd);
       
       // Listening to Send Button
       sendBtn.setOnClickListener(new View.OnClickListener() {
    	   public void onClick(View v) {
    		   emailAddresses = emailAddET.getText().toString().trim();
    		   respondents = new ArrayList<User>();
    		   
    		   //when there is no input on email edit text
    		   if (emailAddresses.length() == 0){
    			   Log.i("emailAdd.length()", "emailAddresses.length() == 0");
    			   Toast.makeText(SendInvitationActivity.this, "You did not enter a valid email address", Toast.LENGTH_LONG).show();
    			   return;
    		   
    		   //when there is more than 1 email add (hence got presence of comma)
    		   } else if (emailAddresses.contains(",")) {
    			   Log.i("emailAdd.contains comma", "email got comma");
    			   emailArray = emailAddresses.split(",");
    			   isValid = false;

    			   for(String emailStr:emailArray){
    				   Log.i("emailArray", emailStr);
    				   
    				   //check if each string s is a valid email add
    				   if(isEmailValid(emailStr)) {
    					   isValid = true;
    					   Log.i("isEmailValid", "isEmailValid1");
    					   User u = eventDAO.getUser(emailStr.trim());
        				   
        				   //check if user exists already by email add
        				   if (u == null) {
        					   Log.i("user", "user is null");
        					   u = new User();
                			   u.setEmail(emailStr);
                			   eventDAO.addUser(u);
        					   Log.i("user", "user is added to dao");
        				   }
        				   
        				   respondents.add(u);
        				   for(User a:respondents){
        					   Log.i("respondentsForLoopUserID", String.valueOf(a.userID));
        				   }
        				   Log.i("respondentsForLoopEventID", String.valueOf(eventID));
        				   eventDAO.addEventRespondents(event, respondents);
        				   Log.i("respondents.size()", String.valueOf(respondents.size()));
        				   
    				   } else {
    					   isValid = false;
    					   Toast.makeText(SendInvitationActivity.this, "You did not enter a valid email address", Toast.LENGTH_LONG).show();
            			   break;
    					   //return;
    				   }
    			   }
    			   
    			   if (isValid){
    				   Log.i("clicks","You clicked Send and Finish button");
    	    	       Intent i = new Intent(SendInvitationActivity.this, PollActivity.class);
    	    	       i.putExtra("emailAdd", email);
    	    	       startActivity(i);
    			   }
    		   
    		   //when there is only 1 email add (no presence of comma)
    		   } else {
    			   Log.i("emailAdd nv contain comma", "only got 1 email add");
    			   
    			   //check if email add is valid
    			   if (isEmailValid(emailAddresses)) {
    				   Log.i("isEmailValid2", "isEmailValid2");
    				   User user = eventDAO.getUser(emailAddresses.trim());
    				   
    				   //check if user exists already by email add
    				   if (user == null) {
    					   Log.i("user", "user is null");
    					   user = new User();
            			   user.setEmail(emailAddresses);
            			   eventDAO.addUser(user);
            			   Log.i("user", "user is added to dao");
    				   }
    				   
    				   respondents.add(user);
    				   eventDAO.addEventRespondents(event, respondents);
    				   Log.i("respondent.size()", String.valueOf(respondents.size()));
    				   
    				   Log.i("clicks","You clicked Send and Finish button");
    	    	       Intent i = new Intent(SendInvitationActivity.this, PollActivity.class);
    	    	       i.putExtra("emailAdd", email);
    	    	       i.putExtra("eventID", eventID);
    	    	       startActivity(i);
    				   
    			   } else {
    				   Toast.makeText(SendInvitationActivity.this, "You did not enter a valid email address", Toast.LENGTH_LONG).show();
        			   return;
    			   }
    		   }
    		   
    	    }
       });
    }
	
	public static boolean isEmailValid(CharSequence email) {
		   return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
	}
}
