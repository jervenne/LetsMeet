package com.mobidev;

import java.util.List;
import java.util.Map;

import com.mobidev.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class PollsUserExpandableListAdapter extends BaseExpandableListAdapter {
	private Activity context;
    private Map<String, List<String>> dateCollections;
    private List<String> dates;
    
    public PollsUserExpandableListAdapter(Activity context, List<String> dates,
            Map<String, List<String>> dateCollections) {
        this.context = context;
        this.dateCollections = dateCollections;
        this.dates = dates;
    }
    
    public Object getChild(int groupPosition, int childPosition) {
        return dateCollections.get(dates.get(groupPosition)).get(childPosition);
    }
    
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    
    public View getChildView(final int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        final String date = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();
 
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.polls_user_child_item, null);
        }
 
        TextView item = (TextView) convertView.findViewById(R.id.meetingDate);
        item.setText(date);
        
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(context, date,
                  Toast.LENGTH_SHORT).show();
            }
          });
        
        return convertView;
    }
    
    public int getChildrenCount(int groupPosition) {
        return dateCollections.get(dates.get(groupPosition)).size();
    }
 
    public Object getGroup(int groupPosition) {
        return dates.get(groupPosition);
    }
 
    public int getGroupCount() {
        return dates.size();
    }
 
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String dateName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.polls_user_group_item,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.meetingDate);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(dateName);
        return convertView;
    }
 
    public boolean hasStableIds() {
        return true;
    }
 
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    
}
