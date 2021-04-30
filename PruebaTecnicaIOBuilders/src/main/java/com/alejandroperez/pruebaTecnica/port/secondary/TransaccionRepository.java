package com.alejandroperez.pruebaTecnica.port.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alejandroperez.pruebaTecnica.domain.Transaccion;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {

}
