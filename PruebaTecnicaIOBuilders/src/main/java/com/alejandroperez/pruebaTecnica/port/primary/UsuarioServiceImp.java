package com.alejandroperez.pruebaTecnica.port.primary;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alejandroperez.pruebaTecnica.domain.Usuario;
import com.alejandroperez.pruebaTecnica.port.secondary.UsuarioRepository;

@Service
public class UsuarioServiceImp implements UsuarioService {

	private UsuarioRepository repository;

	public UsuarioServiceImp(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public Usuario guardarUsuario(Usuario usuario) {
		return repository.save(usuario);

	}

	@Override
	public Optional<Usuario> getUsuarioById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Usuario> getUsuarioByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public List<Usuario> getListUsuarios() {
		return repository.findAll();
	}

}
