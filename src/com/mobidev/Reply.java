package com.mobidev;

public class Reply {

	User user;
	Option option;
	Event event;
	
	public Reply(User user, Option option, Event event) {
		super();
		this.user = user;
		this.option = option;
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
}

