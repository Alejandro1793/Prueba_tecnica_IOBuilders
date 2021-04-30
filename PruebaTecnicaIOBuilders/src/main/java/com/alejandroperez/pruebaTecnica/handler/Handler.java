package com.alejandroperez.pruebaTecnica.handler;

import com.alejandroperez.pruebaTecnica.event.Event;

public interface Handler<E extends Event> {

	void onEvent(E event);

}
