package com.alejandroperez.pruebaTecnica.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CUENTAS")
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CUENTAS_PK")
	private Integer id;

	@Column(name = "S_IBAN")
	private String iban;

	@Column(name = "S_SALDO")
	private Double saldo;

	@JsonIgnoreProperties({ "cuenta", "hibernateLazyInitializer" })
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USUARIO_FK", nullable = false, updatable = false)
	private Usuario usuario;

	@JsonIgnoreProperties({ "cuenta", "hibernateLazyInitializer" })
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cuenta", cascade = CascadeType.ALL)
	private List<Transaccion> transacciones;

	// CONSTRUCTORES

	public Cuenta() {
	}

	public Cuenta(String iban, Double saldo, Usuario usuario) {
		this.iban = iban;
		this.saldo = saldo;
		this.usuario = usuario;
	}

	// GETTERS / SETTERS

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Transaccion> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}

	// HASHCODE / EQUALS / TOSTRING

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iban == null) ? 0 : iban.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((transacciones == null) ? 0 : transacciones.hashCode());
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
		Cuenta other = (Cuenta) obj;
		if (iban == null) {
			if (other.iban != null)
				return false;
		} else if (!iban.equals(other.iban))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (transacciones == null) {
			if (other.transacciones != null)
				return false;
		} else if (!transacciones.equals(other.transacciones))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", iban=" + iban + ", saldo=" + saldo + ", transacciones=" + transacciones
				+ ", usuario=" + usuario + "]";
	}

}
