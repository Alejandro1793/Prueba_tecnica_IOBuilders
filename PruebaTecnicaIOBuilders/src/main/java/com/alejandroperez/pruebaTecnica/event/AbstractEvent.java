package com.alejandroperez.pruebaTecnica.event;

public abstract class AbstractEvent implements Event {

	public Class<? extends Event> getType() {
		return getClass();
	}

}
