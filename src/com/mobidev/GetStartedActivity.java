package com.mobidev;

import com.mobidev.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GetStartedActivity extends Activity{
	
	String emailStr;
	String email;
	boolean hasUser;
	EventDAO eventDAO;
	EditText emailAddET;
	Button startBtn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);  
       //loadSavedPreferences();
       eventDAO = new EventDAO(this);
       
       setContentView(R.layout.start_page); 
       startBtn = (Button) findViewById(R.id.startButton);
       emailAddET = (EditText) findViewById(R.id.emailAdd);
        
        // Listening to Start Button
       startBtn.setOnClickListener(new View.OnClickListener() {
    	   public void onClick(View v) {
    		   
    		   emailStr = emailAddET.getText().toString().trim();
    		   Log.d("emailEditText", emailStr);
    		   Log.d("isEmailValid", String.valueOf(isEmailValid(emailStr)));
    		   
    		   if (isEmailValid(emailStr)) {
    			   //savePreferences("email", emailStr);
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
        	        Intent i = new Intent(GetStartedActivity.this, EventListActivity.class);
        	        i.putExtra("emailAdd", emailStr);
        	        startActivity(i);
    		   } else {
    			   Toast.makeText(GetStartedActivity.this, "You did not enter a valid email address", Toast.LENGTH_LONG).show();
    			   return;
    		   }
    		   
    	    }
       });
    }
	
	public static boolean isEmailValid(CharSequence email) {
		   return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
	}
	
	/*
	private void loadSavedPreferences(){
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		String savedEmail = sharedPreferences.getString("email", "");
		emailAddET.setText(savedEmail);
	}
	
	private void savePreferences(String key, String value) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	*/
}
