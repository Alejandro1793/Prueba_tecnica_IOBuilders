package com.alejandroperez.pruebaTecnica.port.secondary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alejandroperez.pruebaTecnica.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public Optional<Usuario> findByEmail(String email);
}
