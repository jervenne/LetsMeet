package com.mobidev;

import com.mobidev.Event;

import java.sql.Date;

public class Option {

	int optionID;
	Event event;
	Date datetime;
	
	public Option(int optionID, Event event, Date datetime) {
		super();
		this.optionID = optionID;
		this.event = event;
		this.datetime = datetime;
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

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
		
}
