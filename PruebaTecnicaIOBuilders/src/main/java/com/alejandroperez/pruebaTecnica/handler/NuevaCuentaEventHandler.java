package com.alejandroperez.pruebaTecnica.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alejandroperez.pruebaTecnica.event.NuevaCuentaEvent;

public class NuevaCuentaEventHandler implements Handler<NuevaCuentaEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(NuevaCuentaEventHandler.class);

	@Override
	public void onEvent(NuevaCuentaEvent event) {
		LOGGER.info("La cuenta '{}' ha sido creada", event.getCuenta().getIban());
	}
}
