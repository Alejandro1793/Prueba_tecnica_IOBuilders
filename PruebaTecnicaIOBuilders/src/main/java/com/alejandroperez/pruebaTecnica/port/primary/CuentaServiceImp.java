package com.alejandroperez.pruebaTecnica.port.primary;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alejandroperez.pruebaTecnica.domain.Cuenta;
import com.alejandroperez.pruebaTecnica.port.secondary.CuentaRepository;

@Service
public class CuentaServiceImp implements CuentaService {

	private CuentaRepository repository;

	public CuentaServiceImp(CuentaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Cuenta guardarCuenta(Cuenta cuenta) {
		return repository.save(cuenta);
	}

	@Override
	public Optional<Cuenta> getCuentaById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public List<Cuenta> getListCuentas() {
		return repository.findAll();
	}

}
