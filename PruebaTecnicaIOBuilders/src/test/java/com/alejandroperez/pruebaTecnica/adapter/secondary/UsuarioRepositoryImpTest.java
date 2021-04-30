package com.alejandroperez.pruebaTecnica.adapter.secondary;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.alejandroperez.pruebaTecnica.domain.Usuario;
import com.alejandroperez.pruebaTecnica.port.secondary.UsuarioRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioRepositoryImpTest {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void shouldFindAllUsuarios() {
		entityManager.persist(new Usuario("Nombre1", "Apellido1", "email1@email.com", "password1"));
		entityManager.persist(new Usuario("Nombre2", "Apellido2", "email2@email.com", "password2"));
		
		List<Usuario> usuarios = repository.findAll();

		assertEquals("Nombre1", usuarios.get(0).getNombre());
		assertEquals("Nombre2", usuarios.get(1).getNombre());
	}

	@Test
	public void shouldFindById() {
		entityManager.persist(new Usuario("Nombre1", "Apellido1", "email1@email.com", "password1"));
		
		Usuario usuarioResult = repository.findById(1).orElse(null);
		
		assertEquals("Nombre1", usuarioResult.getNombre());
	}

}
