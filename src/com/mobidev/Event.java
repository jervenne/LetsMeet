package com.mobidev;

import java.util.ArrayList;

public class Event {

	int eventID;
	String eventName;
	String location;
	String description;
	int owner;
	ArrayList<User> respondents;
	ArrayList<Option> options;
	
	public Event(){
		
	}
	
	public Event(int eventID, String eventName, String location,
			String description, int owner, ArrayList<User> respondents) {
		super();
		this.eventID = eventID;
		this.eventName = eventName;
		this.location = location;
		this.description = description;
		this.owner = owner;
		this.respondents = respondents;
	}

	public Event(int eventID, String eventName, int owner,
			ArrayList<User> respondents) {
		super();
		this.eventID = eventID;
		this.eventName = eventName;
		this.owner = owner;
		this.respondents = respondents;
	}

	public Event(int eventID, String eventName, String location,
			String description, int owner, ArrayList<User> respondents,
			ArrayList<Option> options) {
		super();
		this.eventID = eventID;
		this.eventName = eventName;
		this.location = location;
		this.description = description;
		this.owner = owner;
		this.respondents = respondents;
		this.options = options;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public ArrayList<User> getRespondents() {
		return respondents;
	}

	public void setRespondents(ArrayList<User> respondents) {
		this.respondents = respondents;
	}

	public ArrayList<Option> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<Option> options) {
		this.options = options;
	}

}
