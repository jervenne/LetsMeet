package com.mobidev;

import com.mobidev.Event;

public class Option {

	int optionID;
	Event event;
	String timeslot;
	
	public Option(int optionID, Event event, String timeslot) {
		super();
		this.optionID = optionID;
		this.event = event;
		this.timeslot = timeslot;
	}

	public Option() {
	}

	public int getOptionID() {
		return optionID;
	}

	public void setOptionID(int optionID) {
		this.optionID = optionID;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}
		
}
