package com.alejandroperez.pruebaTecnica.event;

import com.alejandroperez.pruebaTecnica.domain.Usuario;

public class NuevoUsuarioEvent extends AbstractEvent {

	private Usuario usuario;

	public NuevoUsuarioEvent(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
