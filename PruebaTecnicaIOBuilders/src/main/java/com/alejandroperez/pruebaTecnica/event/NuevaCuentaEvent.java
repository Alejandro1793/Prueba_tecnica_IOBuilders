package com.alejandroperez.pruebaTecnica.event;

import com.alejandroperez.pruebaTecnica.domain.Cuenta;

public class NuevaCuentaEvent extends AbstractEvent {

	private Cuenta cuenta;

	public NuevaCuentaEvent(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

}
