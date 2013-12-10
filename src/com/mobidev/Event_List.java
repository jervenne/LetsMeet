package com.mobidev;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Event_List extends Fragment implements ActionBar.TabListener {
	
	MySQLiteHelper eventDB;
	private Fragment thisFragment;
	
	ListView eventList;
	TextView eventListLabel;
	List<Event> events;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActivity().setContentView(R.layout.event_list);
		
		eventDB = new MySQLiteHelper(getActivity());
		
		eventList = (ListView) getActivity().findViewById(R.id.eventList);
		eventListLabel = (TextView) getActivity().findViewById(R.id.eventListLabel);
		
		events = eventDB.getAllEvents();
		
		ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(getActivity(), R.layout.list_item, events);
		eventList.setAdapter(adapter);
		
		eventListLabel.append(" (" + eventDB.getEventCount() + ")");
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		thisFragment = new Event_List();
		ft.add(android.R.id.content, thisFragment);
		ft.attach(thisFragment);
	}
	
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		ft.remove(thisFragment);
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}
}
