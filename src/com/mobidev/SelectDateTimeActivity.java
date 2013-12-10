package com.mobidev;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;

public class SelectDateTimeActivity extends Activity {
	Dialog picker;
	Button addDateTimeButton;
	Button btnSet;
	Button nextButton;
	TimePicker starttimep;
	TimePicker endtimep;
	DatePicker datep;
	Integer startHour, startMinute, endHour, endMinute, month, day, year;
	TextView startTime, endTime, date;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.select_date_time);
			
			addDateTimeButton = (Button) findViewById(R.id.addDateTimeButton);
			nextButton = (Button) findViewById(R.id.nextButton);
			startTime = (TextView) findViewById(R.id.textStartTime);
			endTime = (TextView) findViewById(R.id.textEndTime);
			date = (TextView) findViewById(R.id.textDate);
			
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
							
							date.setText("The date is " + day + "/" + month + "/" + year);
							startTime.setText("Start Time is " + startHour + ":" + startMinute);
							endTime.setText("End Time is " + endHour + ":" + endMinute);
							
							picker.dismiss();
						}
					});
					picker.show();
					
				}
			});
			
			nextButton.setOnClickListener(new View.OnClickListener() {
	    	   public void onClick(View v) {
	    	    	Log.i("clicks","You clicked Send Invitation button");
	    	        Intent i = new Intent(SelectDateTimeActivity.this, SendInvitationActivity.class);
	    	        startActivity(i);
	    	    }
		    });
			
		}
}
