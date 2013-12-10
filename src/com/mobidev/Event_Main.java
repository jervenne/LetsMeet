package com.mobidev;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

public class Event_Main extends Activity {
Tab tab;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.student_main);
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		tab = actionBar.newTab().setTabListener(new Schedule_Event());
		tab.setText("Schedule Event");
		actionBar.addTab(tab);
		
		tab = actionBar.newTab().setTabListener(new Event_List());
		tab.setText("View Events List");
		actionBar.addTab(tab);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
}
