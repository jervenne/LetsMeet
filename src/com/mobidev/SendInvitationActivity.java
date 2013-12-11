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
	Button selectDateBtn;
	EventDAO eventDAO;
	String[] emailArray;
	String emailAddresses;
	String email;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       // Set View to send_invitation.xml
       setContentView(R.layout.send_invitation);
       
       Intent i = getIntent();
	   email = i.getStringExtra("emailAdd");
       
       eventDAO = new EventDAO(this);
       
       selectDateBtn = (Button) findViewById(R.id.sendButton);
       emailAddET = (EditText) findViewById(R.id.emailAdd);
       
       // Listening to Send Button
       selectDateBtn.setOnClickListener(new View.OnClickListener() {
    	   public void onClick(View v) {
    		   emailAddresses = emailAddET.getText().toString().trim();

    		   //when there is no input on email edit text
    		   if (emailAddresses.length() == 0){
    			   Toast.makeText(SendInvitationActivity.this, "You did not enter a valid email address", Toast.LENGTH_LONG).show();
    			   return;
    		   
    		   //when there is more than 1 email add (hence got presence of comma)
    		   } else if (emailAddresses.contains(",")) {
    			   emailArray = emailAddresses.split(",");
    			   
    			   for(String emailStr:emailArray){
    				   //check if each string s is a valid email add
    				   if(isEmailValid(emailStr)) {
    					   User a = eventDAO.getUser(emailStr.trim());
        				   
        				   //check if user exists already by email add
        				   if (a == null) {
        					   User b = new User();
                			   b.setEmail(emailStr);
                			   eventDAO.addUser(b);
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
    		   
    		   //when there is only 1 email add (no presence of comma)
    		   } else {
    			   
    			   //check if email add is valid
    			   if (isEmailValid(emailAddresses)) {
    				   User user = eventDAO.getUser(emailAddresses.trim());
    				   
    				   //check if user exists already by email add
    				   if (user == null) {
    					   User u = new User();
            			   u.setEmail(emailAddresses);
            			   eventDAO.addUser(u);
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
