package com.alejandroperez.pruebaTecnica.adapter.secondary;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.alejandroperez.pruebaTecnica.TipoMovimiento;
import com.alejandroperez.pruebaTecnica.domain.Cuenta;
import com.alejandroperez.pruebaTecnica.domain.Transaccion;
import com.alejandroperez.pruebaTecnica.domain.Usuario;
import com.alejandroperez.pruebaTecnica.port.secondary.TransaccionRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransaccionRepositoryImpTest {

	@Autowired
	private TransaccionRepository repository;

	@Autowired
	private TestEntityManager entityManager;

	private Usuario usuario;

	private Cuenta cuenta;

	private Transaccion transaccion;

	private Transaccion transaccion2;

	@Before
	public void setUp() {
		usuario = new Usuario("Nombre1", "Apellido1", "email1@email.com", "password1");
		cuenta = new Cuenta("ES1111111111111111111111", 0.0, usuario);
		transaccion = new Transaccion(TipoMovimiento.INGRESO, 500.0, cuenta);
		transaccion2 = new Transaccion(TipoMovimiento.GASTO, 300.0, cuenta);
	}

	@Test
	public void shouldFindAllTransacciones() {
		entityManager.persist(usuario);
		entityManager.persist(cuenta);
		entityManager.persist(transaccion);
		entityManager.persist(transaccion2);

		List<Transaccion> transacciones = repository.findAll();

		assertEquals(TipoMovimiento.INGRESO, transacciones.get(0).getTipoMovimiento());
		assertEquals(TipoMovimiento.GASTO, transacciones.get(1).getTipoMovimiento());
	}

	@Test
	public void shouldFindById() {
		entityManager.persist(usuario);
		entityManager.persist(cuenta);
		entityManager.persist(transaccion);

		Transaccion transaccionResult = repository.findById(1).orElse(null);

		assertEquals(TipoMovimiento.INGRESO, transaccionResult.getTipoMovimiento());
	}

}
