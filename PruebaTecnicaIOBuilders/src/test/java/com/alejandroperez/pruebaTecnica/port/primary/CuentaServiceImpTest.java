package com.alejandroperez.pruebaTecnica.port.primary;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.alejandroperez.pruebaTecnica.domain.Cuenta;
import com.alejandroperez.pruebaTecnica.port.secondary.CuentaRepository;

public class CuentaServiceImpTest {

	private CuentaRepository repository = mock(CuentaRepository.class);

	private CuentaService service = new CuentaServiceImp(repository);

	private Cuenta cuenta = mock(Cuenta.class);

	@Test
	public void shouldGuardarCuenta() {
		service.guardarCuenta(cuenta);

		verify(repository).save(cuenta);
	}

	@Test
	public void shouldGetAllCuentas() {
		service.getListCuentas();

		verify(repository).findAll();
	}

	@Test
	public void shouldGetCuentaById() {
		service.getCuentaById(cuenta.getId());

		verify(repository).findById(cuenta.getId());
	}
}
