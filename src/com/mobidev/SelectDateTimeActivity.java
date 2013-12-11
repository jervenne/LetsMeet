package com.mobidev;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;

public class SelectDateTimeActivity extends Activity {
	Dialog picker;
	Button addDateTimeButton, btnSet, nextButton;
	TimePicker starttimep, endtimep;
	DatePicker datep;
	Integer startHour, startMinute, endHour, endMinute, month, day, year;
	TextView startTime, endTime, date;
	String email, startTimeStr, endTimeStr, dateStr;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.select_date_time);
			
			addDateTimeButton = (Button) findViewById(R.id.addDateTimeButton);
			nextButton = (Button) findViewById(R.id.nextButton);
			startTime = (TextView) findViewById(R.id.textStartTime);
			endTime = (TextView) findViewById(R.id.textEndTime);
			date = (TextView) findViewById(R.id.textDate);
			
			Intent i = getIntent();
		    email = i.getStringExtra("emailAdd");
			
			addDateTimeButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					// TODO Auto-generated method stub
					picker = new Dialog(SelectDateTimeActivity.this);
					picker.setContentView(R.layout.date_time_picker_frag);
					picker.setTitle("Select Date and Time");
					
					datep = (DatePicker) picker.findViewById(R.id.datePicker);
					starttimep = (TimePicker) picker.findViewById(R.id.startTimePicker);
					endtimep = (TimePicker) picker.findViewById(R.id.endTimePicker);
					btnSet = (Button) picker.findViewById(R.id.btnSet);

					btnSet.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View view) {
							// TODO Auto-generated method stub
							month = datep.getMonth();
							day = datep.getDayOfMonth();
							year = datep.getYear();
							startHour = starttimep.getCurrentHour();
							startMinute = starttimep.getCurrentMinute();
							endHour = endtimep.getCurrentHour();
							endMinute = endtimep.getCurrentMinute();
							
							Log.i("dateStr", dateStr);
							dateStr = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
							date.setText("The date is " + dateStr);
							
							startTimeStr = String.valueOf(startHour) + ":" + String.valueOf(startMinute);
							startTime.setText("Start Time is " +  startTimeStr);
							
							endTimeStr = String.valueOf(endHour) + ":" + String.valueOf(endMinute);
							endTime.setText("End Time is " + endTimeStr);
							
							picker.dismiss();
						}
					});
					picker.show();
					
				}
			});
			
			nextButton.setOnClickListener(new View.OnClickListener() {
	    	   public void onClick(View v) {
	    		   
	    		   //if(dateStr != null && startTimeStr != null && endTimeStr != null) {
	    			   //if (dateStr.length() > 0 && startTimeStr.length() > 0 && endTimeStr.length() > 0){
		    			   Log.i("clicks","You clicked Send Invitation button");
			    	       Intent i = new Intent(SelectDateTimeActivity.this, SendInvitationActivity.class);
			    	       i.putExtra("emailAdd", email);
			    	       startActivity(i);
		    		/*	   
		    		   } 
	    			   
	    		   } else {
	    			   Toast.makeText(SelectDateTimeActivity.this, "You did not select the date/time", Toast.LENGTH_LONG).show();
	    			   return;
	    		   }
		    		*/
	    	    }
		    });
			
		}
}
