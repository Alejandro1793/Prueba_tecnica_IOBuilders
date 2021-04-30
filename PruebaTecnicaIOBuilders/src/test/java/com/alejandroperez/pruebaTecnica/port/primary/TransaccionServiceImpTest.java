package com.alejandroperez.pruebaTecnica.port.primary;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.alejandroperez.pruebaTecnica.domain.Transaccion;
import com.alejandroperez.pruebaTecnica.port.secondary.TransaccionRepository;

public class TransaccionServiceImpTest {

	private TransaccionRepository repository = mock(TransaccionRepository.class);

	private TransaccionService service = new TransaccionServiceImp(repository);

	private Transaccion transaccion = mock(Transaccion.class);

	@Test
	public void shouldGuardarTransaccion() {
		service.guardarTransaccion(transaccion);

		verify(repository).save(transaccion);
	}

	@Test
	public void shouldGetAllTransacciones() {
		service.getListTransacciones();

		verify(repository).findAll();
	}

	@Test
	public void shouldGetTransaccionById() {
		service.getTransaccionById(transaccion.getId());

		verify(repository).findById(transaccion.getId());
	}

}
