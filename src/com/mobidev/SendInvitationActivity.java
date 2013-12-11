package com.mobidev;

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
	String[] emailArray;
	String email, emailAddresses;
	boolean isValid;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       // Set View to send_invitation.xml
       setContentView(R.layout.send_invitation);
       
       Intent i = getIntent();
	   email = i.getStringExtra("emailAdd");
       
       eventDAO = new EventDAO(this);
       
       sendBtn = (Button) findViewById(R.id.sendButton);
       emailAddET = (EditText) findViewById(R.id.emailAdd);
       
       // Listening to Send Button
       sendBtn.setOnClickListener(new View.OnClickListener() {
    	   public void onClick(View v) {
    		   emailAddresses = emailAddET.getText().toString().trim();

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
    					   
    					   Log.i("isEmailValid", "isEmailValid1");
    					   User a = eventDAO.getUser(emailStr.trim());
        				   
        				   //check if user exists already by email add
        				   if (a == null) {
        					   Log.i("user", "user is null");
        					   User b = new User();
                			   b.setEmail(emailStr);
                			   eventDAO.addUser(b);
        					   Log.i("user", "user is added to dao");
        				   }
        				   isValid = true;
    				   } else {
    					   isValid = false;
    					   Toast.makeText(SendInvitationActivity.this, "You did not enter a valid email address", Toast.LENGTH_LONG).show();
            			   break;
    					   //return;
    				   }
    			   }
    			   
    			   if (isValid){
    				   Log.i("clicks","You clicked Send Invitation button");
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
    					   User u = new User();
            			   u.setEmail(emailAddresses);
            			   eventDAO.addUser(u);
            			   Log.i("user", "user is added to dao");
    				   }
    				   
    				   Log.i("clicks","You clicked Send Invitation button");
    	    	       Intent i = new Intent(SendInvitationActivity.this, PollActivity.class);
    	    	       i.putExtra("emailAdd", email);
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
