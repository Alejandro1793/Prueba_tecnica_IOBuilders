package com.alejandroperez.pruebaTecnica.adapter.primary;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.alejandroperez.pruebaTecnica.TipoMovimiento;
import com.alejandroperez.pruebaTecnica.domain.Cuenta;
import com.alejandroperez.pruebaTecnica.domain.Transaccion;
import com.alejandroperez.pruebaTecnica.domain.Usuario;
import com.alejandroperez.pruebaTecnica.port.primary.UsuarioService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ApplicationControllerTest {

	@Autowired
	public MockMvc mockMvc;

	@Autowired
	private UsuarioService usuarioService;

	Usuario usuario;

	Cuenta cuenta;

	Transaccion transaccion;

	@Before
	public void setUp() {
		usuario = new Usuario("Pedro", "Martinez", "pedro@email.com", "1234");
		cuenta = new Cuenta();
		cuenta.setIban("ES1111111111111111111111");
		transaccion = new Transaccion();
		transaccion.setTipoMovimiento(TipoMovimiento.INGRESO);
		transaccion.setCantidad(0.0);
	}

	public String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	public <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	public void shouldRegistrarUsuario() throws Exception {
		String uri = "/usuario/registrar";
		String inputJson = mapToJson(usuario);
		mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().isOk());

	}

	@Test
	public void shouldCrearCuenta() throws Exception {
		String uri = "/usuario/cuenta/crear";
		Map<String, Object> sesion = new HashMap<>();
		usuario = usuarioService.getUsuarioById(1).get();
		sesion.put("usuario", usuario);
		String inputJson = mapToJson(cuenta);
		mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(inputJson).sessionAttrs(sesion))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldVisualizarCuenta() throws Exception {
		String uri = "/usuario/cuenta/{id}";
		MvcResult mvcResult = mockMvc.perform(get(uri, 1).accept(MediaType.APPLICATION_JSON)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(302, status);
		String content = mvcResult.getResponse().getContentAsString();
		Cuenta cuentaResult = mapFromJson(content, Cuenta.class);
		assertTrue(cuentaResult != null);
	}

	@Test
	public void shouldIngresar() throws Exception {
		String uri = "/usuario/cuenta/1/ingresar";

		String inputJson = mapToJson(transaccion);
		mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().isOk());
	}

}
