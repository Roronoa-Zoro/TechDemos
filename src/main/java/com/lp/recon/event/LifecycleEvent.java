package com.lp.recon.event;

import java.util.Observable;

import com.lp.recon.lifecycle.Lifecycle;

public abstract class LifecycleEvent extends Observable implements Lifecycle {

	public static final String STOP = "stop";
	private String eventType;
	
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public void setLifecycleEventListener(LifecycleEventListener listener) {
		super.addObserver(listener);
	}
	
	public void fireLifecycleEvent(String event) {
		super.setChanged();
		super.notifyObservers(event);
	}

}
