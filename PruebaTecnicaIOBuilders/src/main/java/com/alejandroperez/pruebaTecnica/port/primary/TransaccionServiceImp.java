package com.alejandroperez.pruebaTecnica.port.primary;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alejandroperez.pruebaTecnica.domain.Transaccion;
import com.alejandroperez.pruebaTecnica.port.secondary.TransaccionRepository;

@Service
public class TransaccionServiceImp implements TransaccionService {

	private TransaccionRepository repository;

	public TransaccionServiceImp(TransaccionRepository repository) {
		this.repository = repository;
	}

	@Override
	public Transaccion guardarTransaccion(Transaccion transaccion) {
		return repository.save(transaccion);
	}

	@Override
	public Optional<Transaccion> getTransaccionById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public List<Transaccion> getListTransacciones() {
		return repository.findAll();
	}

}
