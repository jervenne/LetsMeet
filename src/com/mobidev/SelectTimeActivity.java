package com.mobidev;

import com.mobidev.R;

import java.util.Calendar;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SelectTimeActivity extends Activity {
	
	final String PREFNAME = "myPrefers";
	private TextView tvDisplayStartTime;
	private TimePicker startTimePicker;
	private TextView tvDisplayEndTime;
	private TimePicker endTimePicker;
	private Button btnSetTime;
	private TextView tvDisplayDate;
	
	private int hour;
	private int minute;
 
	static final int TIME_DIALOG_ID = 999;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_time);
		
		setCurrentTimeOnView();
		addListenerOnButton();
		
		tvDisplayDate = (TextView) findViewById(R.id.date);
		Button selectDateBtn = (Button) findViewById(R.id.addTimeButton);
        
		//SharedPreferences savedValues = getSharedPreferences(PREFNAME, MODE_PRIVATE);
		
		
        // Listening to Select Date Button
        selectDateBtn.setOnClickListener(new View.OnClickListener() {
    	   public void onClick(View v) {
    	    	Log.i("clicks","You clicked Add Time button");
    	        Intent i = new Intent(SelectTimeActivity.this, SendInvitationActivity.class);
    	        startActivity(i);
    	    }
       });
		
	}
	
	// display current time
	public void setCurrentTimeOnView() {
 
		tvDisplayStartTime = (TextView) findViewById(R.id.startTime);
		tvDisplayEndTime = (TextView) findViewById(R.id.endTime);
		startTimePicker = (TimePicker) findViewById(R.id.startTimePicker);
		endTimePicker = (TimePicker) findViewById(R.id.endTimePicker);
		
		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
 
		// set current time into textview
		tvDisplayStartTime.setText(
                    new StringBuilder().append(pad(hour))
                                       .append(":").append(pad(minute)));
		tvDisplayEndTime.setText(
                new StringBuilder().append(pad(hour))
                                   .append(":").append(pad(minute)));
		
		// set current time into timepicker
		startTimePicker.setCurrentHour(hour);
		startTimePicker.setCurrentMinute(minute);
		endTimePicker.setCurrentHour(hour);
		endTimePicker.setCurrentMinute(minute);
	}
	
	public void addListenerOnButton() {
		 
		btnSetTime = (Button) findViewById(R.id.setTimeButton);
 
		btnSetTime.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
 
				showDialog(TIME_DIALOG_ID);
 
			}
 
		});
 
	}
 
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG_ID:
			// set time picker as current time
			return new TimePickerDialog(this, 
                                        timePickerListener, hour, minute, false);
 
		}
		return null;
	}
 
	private TimePickerDialog.OnTimeSetListener timePickerListener = 
            new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;
 
			// set current time into textview
			tvDisplayStartTime.setText(new StringBuilder().append(pad(hour))
					.append(":").append(pad(minute)));
			tvDisplayEndTime.setText(new StringBuilder().append(pad(hour))
					.append(":").append(pad(minute)));
			
			// set current time into timepicker
			startTimePicker.setCurrentHour(hour);
			startTimePicker.setCurrentMinute(minute);
			endTimePicker.setCurrentHour(hour);
			endTimePicker.setCurrentMinute(minute);
 
		}
	};
 
	private static String pad(int c) {
		if (c >= 10)
		   return String.valueOf(c);
		else
		   return "0" + String.valueOf(c);
	}
}
