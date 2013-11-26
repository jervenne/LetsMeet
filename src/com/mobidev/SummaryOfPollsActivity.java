package com.mobidev;

import android.app.ActionBar; 
import android.app.ActionBar.Tab; 
import android.app.ExpandableListActivity;
import android.os.Bundle; 
import android.content.Intent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.mobidev.PollsUserExpandableListAdapter;

public class SummaryOfPollsActivity extends ExpandableListActivity {
	// Declare Tab Variable 
	Tab tab; 
	List<String> groupList;
    List<String> childList;
    Map<String, List<String>> dateCollection;
    ExpandableListView expListView;
    
	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		Log.i("poll","Start onCreate of SummaryOfPollsActivity");
		
		 super.onCreate(savedInstanceState); 
		 // Create the actionbar 
		 ActionBar actionBar = getActionBar(); 

		 // Create Actionbar Tabs 
		 actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); 

		 // Create first Tab 
		 tab = actionBar.newTab().setTabListener(new PollsByUserFragment()); 
		 // Set Tab Title 
		 tab.setText("Initiated by me"); 
		 actionBar.addTab(tab); 

		 // Create Second Tab 	
		 tab = actionBar.newTab().setTabListener(new PollsByOthersFragment()); 	
		 // Set Tab Title 	
		 tab.setText("Initiated by others"); 
		 actionBar.addTab(tab);
		 
		 //setContentView(R.layout.polls_by_user);
		 
		 Button nextBtn = (Button) findViewById(R.id.nextButton);
	        
	     // Listening to Summary Of Polls button
	     nextBtn.setOnClickListener(new View.OnClickListener() {
	    	 public void onClick(View v) {
    	    	Log.i("clicks","You clicked Summary Of Polls button");
    	        Intent i = new Intent(SummaryOfPollsActivity.this, ScheduleEventActivity.class);
    	        startActivity(i);
    	    }
	     });
		 
		 //for expandable list view
		 try {
			 
			setContentView(R.layout.polls_by_user);
			System.out.print("bbb");
		    createGroupList();
		 
		    createCollection();
		 
		    //expListView = (ExpandableListView) findViewById(R.id.pollsByMeListView);
		    
		    expListView = getExpandableListView();
		    expListView.setDividerHeight(2);
		    expListView.setGroupIndicator(null);
		    expListView.setClickable(true);
		    
		    final PollsUserExpandableListAdapter expListAdapter = new PollsUserExpandableListAdapter(
		                this, groupList, dateCollection);
		    expListView.setAdapter(expListAdapter);
		 
		    //setGroupIndicatorToRight();
		 
		    expListView.setOnChildClickListener(new OnChildClickListener() {
		 
	           public boolean onChildClick(ExpandableListView parent, View v,
	                    int groupPosition, int childPosition, long id) {
	               final String selected = (String) expListAdapter.getChild(
	                       groupPosition, childPosition);
	               Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
	                       .show();
	 
	               return true;
	           }
	        });
		     
		 } catch (Exception e){
			 System.out.print("aaaaaaaaa");
		 }
 
	 }
	 
	 private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("MOBIDEV");
        groupList.add("GENDERS");
        groupList.add("NTROPSY");
        groupList.add("PROJMGT");
        groupList.add("NETWORK");
        groupList.add("INTROOS");
	 }
	 
    private void createCollection() {
        // preparing collection(child)
        String[] mobidev = { "01 October", "19 October",
                "31 October" };
        String[] genders = { "10 October", "21 October", "01 November" };
        String[] ntropsy = { "01 November", "02 November",
                "04 November", "05 November" };
        String[] projmgt = { "05 November", "07 November",
                "09 November", "11 November" };
        String[] network = { "13 November", "14 November",
                "15 November" };
        String[] introos = { "17 November", "18 November",
                "19 November" };
 
        dateCollection = new LinkedHashMap<String, List<String>>();
 
        for (String proj : groupList) {
            if (proj.equals("MOBIDEV")) {
                loadChild(mobidev);
            } else if (proj.equals("GENDERS"))
                loadChild(genders);
            else if (proj.equals("NTROPSY"))
                loadChild(ntropsy);
            else if (proj.equals("PROJMGT"))
                loadChild(projmgt);
            else if (proj.equals("NETWORK"))
                loadChild(network);
            else
                loadChild(introos);
 
            dateCollection.put(proj, childList);
        }
    }
	 
	    private void loadChild(String[] meetings) {
	        childList = new ArrayList<String>();
	        for (String mtg : meetings)
	            childList.add(mtg);
	    }
	 
	    
	    private void setGroupIndicatorToRight() {
	        // Get the screen width 
	        DisplayMetrics dm = new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(dm);
	        int width = dm.widthPixels;
	 
	        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
	                - getDipsFromPixel(5));
	    }
	 
	    // Convert pixel to dip
	    public int getDipsFromPixel(float pixels) {
	        // Get the screen's density scale
	        final float scale = getResources().getDisplayMetrics().density;
	        // Convert the dps to pixels, based on density scale
	        return (int) (pixels * scale + 0.5f);
	    }
	 
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.polls_by_user, menu);
	        return true;
	    }
	    
}
