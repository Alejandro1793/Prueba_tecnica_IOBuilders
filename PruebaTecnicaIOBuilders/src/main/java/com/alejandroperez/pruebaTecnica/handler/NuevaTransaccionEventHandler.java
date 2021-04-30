package com.alejandroperez.pruebaTecnica.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alejandroperez.pruebaTecnica.event.NuevaTransaccionEvent;

public class NuevaTransaccionEventHandler implements Handler<NuevaTransaccionEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(NuevaTransaccionEventHandler.class);

	@Override
	public void onEvent(NuevaTransaccionEvent event) {
		LOGGER.info("Se ha realizado un {} de '{}'", event.getTransaccion().getTipoMovimiento(),
				event.getTransaccion().getCantidad());
	}

}
