package com.alejandroperez.pruebaTecnica.event;

import com.alejandroperez.pruebaTecnica.domain.Transaccion;

public class NuevaTransaccionEvent extends AbstractEvent {

	private Transaccion transaccion;

	public NuevaTransaccionEvent(Transaccion transaccion) {
		this.transaccion = transaccion;
	}

	public Transaccion getTransaccion() {
		return transaccion;
	}
}
