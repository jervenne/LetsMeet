package com.mobidev;

import com.mobidev.R;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class SelectDateActivity extends Activity{
	private TextView tvDisplayDate;
	private DatePicker dpResult;
	private Button btnChangeDate;

	private int year;
	private int month;
	private int day;

	static final int DATE_DIALOG_ID = 999;
	
	final String PREFNAME = "myPrefers";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_date);

		setCurrentDateOnView();
		addListenerOnButton();
		
		Button selectDateBtn = (Button) findViewById(R.id.addDateButton);
        
        // Listening to Select Date Button
       selectDateBtn.setOnClickListener(new View.OnClickListener() {
    	   public void onClick(View v) {
    	    	Log.i("clicks","You clicked Add Date button");
    	        Intent i = new Intent(SelectDateActivity.this, SelectTimeActivity.class);
    	        startActivity(i);
    	    }
       });
		
	}

	// display current date
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_date, menu);
		return true;
	}
	
	public void setCurrentDateOnView() {

		tvDisplayDate = (TextView) findViewById(R.id.tvDate);
		dpResult = (DatePicker) findViewById(R.id.dpResult);

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		// set current date into textview
		tvDisplayDate.setText(new StringBuilder()
				// Month is 0 based, just add 1
				.append(month + 1).append("-").append(day).append("-")
				.append(year).append(" "));

		// set current date into datepicker
		dpResult.init(year, month, day, null);

	}

	public void addListenerOnButton() {

		btnChangeDate = (Button) findViewById(R.id.setDateButton);

		btnChangeDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showDialog(DATE_DIALOG_ID);

			}

		});

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
			case DATE_DIALOG_ID:
				// set date picker as current date
				return new DatePickerDialog(this, datePickerListener, year, month,
						day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			tvDisplayDate.setText(new StringBuilder().append(month + 1)
					.append("-").append(day).append("-").append(year)
					.append(" "));

			// set selected date into datepicker also
			dpResult.init(year, month, day, null);

		}
	};
}
