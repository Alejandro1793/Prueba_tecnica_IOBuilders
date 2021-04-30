package com.alejandroperez.pruebaTecnica.event;

public interface Event {

	Class<? extends Event> getType();

}
