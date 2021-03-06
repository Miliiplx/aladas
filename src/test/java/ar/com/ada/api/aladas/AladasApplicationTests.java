package ar.com.ada.api.aladas;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.ada.api.aladas.entities.Aeropuerto;
import ar.com.ada.api.aladas.entities.Usuario;
import ar.com.ada.api.aladas.entities.Vuelo;
import ar.com.ada.api.aladas.entities.Vuelo.EstadoVueloEnum;
import ar.com.ada.api.aladas.security.Crypto;
import ar.com.ada.api.aladas.services.AeropuertoService;
import ar.com.ada.api.aladas.services.VueloService;
import ar.com.ada.api.aladas.services.VueloService.ValidacionVueloDataEnum;

@SpringBootTest
class AladasApplicationTests {

	@Autowired
	VueloService vueloService;

	@Autowired
	AeropuertoService aeropuertoService;

	@Test
	void vueloTestPrecio() {

		Vuelo vueloConPrecioNegativo = new Vuelo();
		vueloConPrecioNegativo.setPrecio(new BigDecimal(-100));

		// Assert: afirma
		// afirma que sea verdadero = assertTrue
		// assertFalse = afirmar que es falso
		assertFalse(vueloService.validarPrecio(vueloConPrecioNegativo));

	}

	@Test
	void vueloTestPrecioOk() {

		Vuelo vueloConPrecioOk = new Vuelo();
		vueloConPrecioOk.setPrecio(new BigDecimal(100));

		assertTrue(vueloService.validarPrecio(vueloConPrecioOk));

	}

	@Test
	void aeropuertoValidarCodigoIATAOk() {

		String codigoIATA1 = "EZE";
		String codigoIATA2 = "A  ";
		String codigoIATA3 = "N6N";

		/*
		 * assertEquals(3, codigoIATA1.length());
		 * 
		 * assertTrue(codigoIATA2.length() == 3);
		 */

		Aeropuerto aeropuerto1 = new Aeropuerto();
		aeropuerto1.setCodigoIATA(codigoIATA1);

		Aeropuerto aeropuerto2 = new Aeropuerto();
		aeropuerto2.setCodigoIATA(codigoIATA2);

		Aeropuerto aeropuerto3 = new Aeropuerto();
		aeropuerto3.setCodigoIATA(codigoIATA3);

		assertTrue(aeropuertoService.validarCodigoIATA(aeropuerto1));
		assertFalse(aeropuertoService.validarCodigoIATA(aeropuerto2));
		assertFalse(aeropuertoService.validarCodigoIATA(aeropuerto3));

	}

	@Test
	void aeropuertoValidarCodigoIATANoOk() {

	}

	@Test
	void vueloVerificarValidacionAeropuertoOrigenDestino() {
		// validar todas las posibilidades de si el aeropuerto
		// origen es igual al de destino, etc
	}

	@Test
	void vueloChequearQueLosPendientesNoTenganVuelosViejos() {

	}

	@Test
	void vueloVerificarCapacidadMaxima() {

	}

	@Test
	void vueloVerificarCapacidadMinima() {

	}

	@Test
	void aeropuertoTestBuscadorIATA() {

	}

	@Test
	void vueloValidarVueloMismoDestino() {
		Vuelo vuelo = new Vuelo();
		vuelo.setPrecio(new BigDecimal(100));
		vuelo.setEstadoVueloId(EstadoVueloEnum.GENERADO);
		vuelo.setAeropuertoOrigen(143);
		vuelo.setAeropuertoDestino(143);

		assertEquals(ValidacionVueloDataEnum.ERROR_AEROPUERTOS_IGUALES, vueloService.validar(vuelo));
	}

	@Test
	void testearEncriptacion() {

		String contrase??aImaginaria = "pitufosasesinos";
		String contrase??aImaginariaEncriptada = Crypto.encrypt(contrase??aImaginaria, "palabra");

		String contrase??aImaginariaEncriptadaDesencriptada = Crypto.decrypt(contrase??aImaginariaEncriptada, "palabra");

		// assertTrue(contrase??aImaginariaEncriptadaDesencriptada.equals(contrase??aImaginaria));
		assertEquals(contrase??aImaginariaEncriptadaDesencriptada, contrase??aImaginaria);
	}

	@Test
	void testearContrase??a() {
		Usuario usuario = new Usuario();

		usuario.setUsername("Diana@gmail.com");
		usuario.setPassword("qp5TPhgUtIf7RDylefkIbw==");
		usuario.setEmail("Diana@gmail.com");

		assertFalse(!usuario.getPassword().equals(Crypto.encrypt("AbcdE23", usuario.getUsername().toLowerCase())));

	}

	/*
	 * @Test void testearAeropuertoId(){ Aeropuerto aeropuerto = new Aeropuerto();
	 * aeropuerto.setAeropuertoId(17); aeropuerto.setCodigoIATA("MDZ");
	 * aeropuerto.setNombre("Aeropuerto Internacional El Plumerillo");
	 * 
	 * assertEquals(ValidacidacionAeropuertoEnum.ERROR_AEROPUERTOS_IGUALES,
	 * aeropuertoService.validar(aeropuerto)); }
	 * 
	 * @Test void testearAeropuertoCodigoIATA(){ Aeropuerto aeropuerto = new
	 * Aeropuerto(); aeropuerto.setAeropuertoId(5539);
	 * aeropuerto.setCodigoIATA("  M");
	 * aeropuerto.setNombre("Aeropuerto Internacional El Plumerillo");
	 * 
	 * assertEquals(Valida, actual); }
	 */

}
