package com.alejandroperez.pruebaTecnica.port.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alejandroperez.pruebaTecnica.domain.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

}
