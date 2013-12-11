package com.mobidev;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DisplayAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<String> eventID;
	private ArrayList<String> eventName;
	private ArrayList<String> location;
	private ArrayList<String> description;
	

	public DisplayAdapter(Context c, ArrayList<String> eventID, ArrayList<String> eventName, ArrayList<String> location, ArrayList<String> description) {
		this.mContext = c;

		this.eventID = eventID;
		this.eventName = eventName;
		this.location = location;
		this.description = description;
	}

	public int getCount() {
		return eventID.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int pos, View child, ViewGroup parent) {
		Holder mHolder;
		LayoutInflater layoutInflater;
		if (child == null) {
			layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			child = layoutInflater.inflate(R.layout.list_item, null);
			mHolder = new Holder();
			//mHolder.txt_id = (TextView) child.findViewById(R.id.txt_id);
			mHolder.txt_eventName = (TextView) child.findViewById(R.id.txt_eventName);
			mHolder.txt_description = (TextView) child.findViewById(R.id.txt_description);
			mHolder.txt_location = (TextView) child.findViewById(R.id.txt_location);
			child.setTag(mHolder);
		} else {
			mHolder = (Holder) child.getTag();
		}
		//mHolder.txt_id.setText(eventID.get(pos));
		mHolder.txt_eventName.setText(eventName.get(pos));
		mHolder.txt_location.setText(location.get(pos));
		mHolder.txt_description.setText(description.get(pos));

		return child;
	}

	public class Holder {
		//TextView txt_id;
		TextView txt_eventName;
		TextView txt_location;
		TextView txt_description;
	}

}
