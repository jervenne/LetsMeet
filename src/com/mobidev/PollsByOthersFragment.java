package com.mobidev;

import com.mobidev.R;

import android.os.Bundle; 
import android.app.ActionBar.Tab; 
import android.app.Fragment; 
import android.app.FragmentTransaction; 
import android.app.ActionBar;

public class PollsByOthersFragment extends Fragment implements ActionBar.TabListener {
	private Fragment mFragment;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//Get the view from polls_by_others.xml
		getActivity().setContentView(R.layout.polls_by_others);
	}
	
	public void onTabSelected(Tab tab, FragmentTransaction ft) { 
		 // TODO Auto-generated method stub 
		 mFragment = new PollsByOthersFragment(); 
		 // Attach polls_by_others.xml layout 
		 ft.add(android.R.id.content, mFragment); 
		 ft.attach(mFragment); 
		 } 
	
	public void onTabUnselected(Tab tab, FragmentTransaction ft) { 
		 // TODO Auto-generated method stub 
		 // Remove polls_by_others.xml layout 
		 ft.remove(mFragment); 
	} 

	public void onTabReselected(Tab tab, FragmentTransaction ft) { 
		 // TODO Auto-generated method stub 
	}
}
