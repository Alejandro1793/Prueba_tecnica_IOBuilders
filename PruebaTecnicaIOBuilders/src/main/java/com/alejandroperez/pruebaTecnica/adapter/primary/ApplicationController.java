package com.alejandroperez.pruebaTecnica.adapter.primary;

import java.util.ArrayList;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandroperez.pruebaTecnica.TipoMovimiento;
import com.alejandroperez.pruebaTecnica.domain.Cuenta;
import com.alejandroperez.pruebaTecnica.domain.Transaccion;
import com.alejandroperez.pruebaTecnica.domain.Usuario;
import com.alejandroperez.pruebaTecnica.event.EventDispatcher;
import com.alejandroperez.pruebaTecnica.event.NuevaCuentaEvent;
import com.alejandroperez.pruebaTecnica.event.NuevaTransaccionEvent;
import com.alejandroperez.pruebaTecnica.event.NuevoUsuarioEvent;
import com.alejandroperez.pruebaTecnica.handler.NuevoUsuarioEventHandler;
import com.alejandroperez.pruebaTecnica.port.primary.CuentaService;
import com.alejandroperez.pruebaTecnica.port.primary.TransaccionService;
import com.alejandroperez.pruebaTecnica.port.primary.UsuarioService;

@RestController
@RequestMapping("usuario")
public class ApplicationController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private CuentaService cuentaService;

	@Autowired
	private TransaccionService transaccionService;

	private EventDispatcher dispatcher = new EventDispatcher();

	@PostConstruct
	public void init() {
		dispatcher.registerHandler(NuevoUsuarioEvent.class, new NuevoUsuarioEventHandler());
	}

	/*
	 * Recoge el objeto obtenido en el cuerpo de la petición web y lo guarda en la
	 * BBDD. Después guarda dicho usuario en la sesión para utilizarlo
	 * posteriormente en la creación de la cuenta
	 */
	@PostMapping("/registrar")
	public void registrarUsuario(@RequestBody Usuario usuario, HttpSession session) {
		usuarioService.guardarUsuario(usuario);
		dispatcher.dispatch(new NuevoUsuarioEvent(usuario));
		session.setAttribute("usuario", usuario);
	}

	/*
	 * Recoge un ojeto Cuenta con el IBAN del cuerpo de la petición web. Le
	 * inicializa el saldo, la lista de transacciones y el usuario anteriormente
	 * recogido en el registro, y lo guarda en la BBDD
	 */
	@PostMapping("/cuenta/crear")
	public void crearCuenta(@RequestBody Cuenta cuenta, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		cuenta.setSaldo(0.0);
		cuenta.setUsuario(usuario);
		cuenta.setTransacciones(new ArrayList<>());
		cuentaService.guardarCuenta(cuenta);
		usuarioService.guardarUsuario(usuario);
		dispatcher.dispatch(new NuevaCuentaEvent(cuenta));
	}

	/* Devuelve el objeto cuenta con el id que se pasa como variable en la url */
	@GetMapping("/cuenta/{id}")
	public ResponseEntity<Cuenta> verCuenta(@PathVariable("id") Integer cuentaId) {
		Optional<Cuenta> optionalCuenta = cuentaService.getCuentaById(cuentaId);
		return optionalCuenta.map(op -> new ResponseEntity<Cuenta>(op, HttpStatus.FOUND))
				.orElseGet(() -> new ResponseEntity<Cuenta>(HttpStatus.NOT_FOUND));
	}

	/*
	 * Recoge un objeto Transaccion con la cantidad y, en este caso, con el tipo de
	 * movimiento INGRESO y lo asigna a la cuenta con el id que se pasa como
	 * variable en la url. Si la cuenta existe se recalcula su saldo y se guardan
	 * tanto la cuenta como la transacción en la BBDD
	 */
	@PostMapping("/cuenta/{id}/ingresar")
	public void depositarDinero(@PathVariable("id") Integer cuentaId, @RequestBody Transaccion transaccion) {
		Optional<Cuenta> optionalCuenta = cuentaService.getCuentaById(cuentaId);
		if (optionalCuenta.isPresent()) {
			recalcularSaldo(optionalCuenta.get(), transaccion);
			transaccion.setCuenta(optionalCuenta.get());
			cuentaService.guardarCuenta(optionalCuenta.get());
			transaccionService.guardarTransaccion(transaccion);
			dispatcher.dispatch(new NuevaTransaccionEvent(transaccion));
		}
	}

	public void recalcularSaldo(Cuenta cuenta, Transaccion transaccion) {
		Double saldo = cuenta.getSaldo();
		if (transaccion.getTipoMovimiento().equals(TipoMovimiento.INGRESO)) {
			saldo += transaccion.getCantidad();
		} else {
			saldo -= transaccion.getCantidad();
		}
		cuenta.setSaldo(saldo);
	}

}
