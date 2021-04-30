package com.alejandroperez.pruebaTecnica.port.primary;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alejandroperez.pruebaTecnica.domain.Transaccion;

@Service
public interface TransaccionService {

	Transaccion guardarTransaccion(Transaccion transaccion);

	Optional<Transaccion> getTransaccionById(Integer id);

	List<Transaccion> getListTransacciones();
}
