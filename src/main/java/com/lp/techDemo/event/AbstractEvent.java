package com.lp.techDemo.event;

public abstract class AbstractEvent implements Event {

	private EventArgument eventArgument;

	public EventArgument getEventArgument() {
		return eventArgument;
	}

	public void setEventArgument(EventArgument eventArgument) {
		this.eventArgument = eventArgument;
	}

	@Override
	public void fireEvent() {
		throw new UnsupportedOperationException("This method is not supported");
	}
	
	public abstract void generateEventArgument();

}
