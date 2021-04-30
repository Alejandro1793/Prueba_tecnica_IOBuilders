package com.alejandroperez.pruebaTecnica.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alejandroperez.pruebaTecnica.event.NuevoUsuarioEvent;

public class NuevoUsuarioEventHandler implements Handler<NuevoUsuarioEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(NuevoUsuarioEventHandler.class);

	@Override
	public void onEvent(NuevoUsuarioEvent event) {
		LOGGER.info("El usuario '{}' con id {} ha sido registrado", event.getUsuario().getNombre(),
				event.getUsuario().getId());
	}

}
