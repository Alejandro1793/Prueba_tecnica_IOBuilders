package com.alejandroperez.pruebaTecnica.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alejandroperez.pruebaTecnica.TipoMovimiento;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "TRANSACCIONES")
public class Transaccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANSACCION_PK")
	private Integer id;

	@Column(name = "TIPO_MOVIMIENTO")
	private TipoMovimiento tipoMovimiento;

	@Column(name = "D_CANTIDAD")
	private Double cantidad;

	@JsonIgnoreProperties({ "transacciones", "hibernateLazyInitializer" })
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CUENTA_FK", nullable = false)
	private Cuenta cuenta;

	// CONSTRUCTORES

	public Transaccion() {
	}

	public Transaccion(TipoMovimiento tipoMovimiento, Double cantidad, Cuenta cuenta) {
		this.tipoMovimiento = tipoMovimiento;
		this.cantidad = cantidad;
		this.cuenta = cuenta;
	}

	// GETTERS / SETTERS

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	// HASHCODE / EQUALS / TOSTRING

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidad == null) ? 0 : cantidad.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tipoMovimiento == null) ? 0 : tipoMovimiento.hashCode());
		result = prime * result + ((cuenta == null) ? 0 : cuenta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaccion other = (Transaccion) obj;
		if (cantidad == null) {
			if (other.cantidad != null)
				return false;
		} else if (!cantidad.equals(other.cantidad))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (cuenta == null) {
			if (other.cuenta != null)
				return false;
		} else if (!cuenta.equals(other.cuenta))
			return false;
		if (tipoMovimiento != other.tipoMovimiento)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaccion [id=" + id + ", tipoMovimiento=" + tipoMovimiento + ", cantidad=" + cantidad + ", cuenta="
				+ cuenta + "]";
	}

}
