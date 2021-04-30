package com.alejandroperez.pruebaTecnica.port.primary;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alejandroperez.pruebaTecnica.domain.Cuenta;

@Service
public interface CuentaService {

	Cuenta guardarCuenta(Cuenta cuenta);

	Optional<Cuenta> getCuentaById(Integer id);

	List<Cuenta> getListCuentas();

}
