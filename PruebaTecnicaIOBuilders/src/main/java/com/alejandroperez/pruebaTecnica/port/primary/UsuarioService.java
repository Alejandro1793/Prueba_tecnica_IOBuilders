package com.alejandroperez.pruebaTecnica.port.primary;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alejandroperez.pruebaTecnica.domain.Usuario;

@Service
public interface UsuarioService {

	Usuario guardarUsuario(Usuario usuario);

	Optional<Usuario> getUsuarioById(Integer id);

	Optional<Usuario> getUsuarioByEmail(String email);

	List<Usuario> getListUsuarios();

}
