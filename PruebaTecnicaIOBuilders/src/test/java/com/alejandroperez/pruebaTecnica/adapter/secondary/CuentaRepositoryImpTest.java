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

import com.alejandroperez.pruebaTecnica.domain.Cuenta;
import com.alejandroperez.pruebaTecnica.domain.Usuario;
import com.alejandroperez.pruebaTecnica.port.secondary.CuentaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CuentaRepositoryImpTest {

	@Autowired
	private CuentaRepository repository;

	@Autowired
	private TestEntityManager entityManager;

	private Usuario usuario;

	private Usuario usuarioAlt;

	@Before
	public void setUp() {
		usuario = new Usuario("Nombre1", "Apellido1", "email1@email.com", "password1");
		usuarioAlt = new Usuario("Nombre2", "Apellido2", "email2@email.com", "password2");
	}

	@Test
	public void shouldFindAllCuentas() {
		entityManager.persist(usuario);
		entityManager.persist(usuarioAlt);
		entityManager.persist(new Cuenta("ES1111111111111111111111", 0.0, usuario));
		entityManager.persist(new Cuenta("ES2222222222222222222222", 0.0, usuarioAlt));

		List<Cuenta> cuentas = repository.findAll();

		assertEquals("ES1111111111111111111111", cuentas.get(0).getIban());
		assertEquals("ES2222222222222222222222", cuentas.get(1).getIban());
	}

	@Test
	public void shouldFindById() {
		entityManager.persist(usuario);
		entityManager.persist(new Cuenta("ES1111111111111111111111", 0.0, usuario));

		Cuenta cuentaResult = repository.findById(1).orElse(null);

		assertEquals("ES1111111111111111111111", cuentaResult.getIban());
	}

}
