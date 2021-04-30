package com.alejandroperez.pruebaTecnica.port.primary;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.alejandroperez.pruebaTecnica.domain.Usuario;
import com.alejandroperez.pruebaTecnica.port.secondary.UsuarioRepository;

public class UsuarioServiceImpTest {

	private UsuarioRepository repository = mock(UsuarioRepository.class);

	private UsuarioService service = new UsuarioServiceImp(repository);

	private Usuario usuario = mock(Usuario.class);

	@Test
	public void shouldGuardarUsuario() {
		service.guardarUsuario(usuario);

		verify(repository).save(usuario);
	}

	@Test
	public void shouldGetAllUsuarios() {
		service.getListUsuarios();

		verify(repository).findAll();
	}

	@Test
	public void shouldGetUsuarioById() {
		service.getUsuarioById(usuario.getId());

		verify(repository).findById(usuario.getId());
	}

}
