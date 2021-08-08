package ar.com.ada.api.aladas;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.ada.api.aladas.entities.Aeropuerto;
import ar.com.ada.api.aladas.entities.Vuelo;
import ar.com.ada.api.aladas.entities.Vuelo.EstadoVueloEnum;
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
	void aeropuertoValidarCodigoIATAOk(){

		String codigoIATA1 = "EZE";
		String codigoIATA2 = "A  ";
		String codigoIATA3 = "N6N";

		/*assertEquals(3, codigoIATA1.length());

		assertTrue(codigoIATA2.length() == 3);*/

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
	void aeropuertoValidarCodigoIATANoOk(){

	}

	@Test
	void vueloVerificarValidacionAeropuertoOrigenDestino(){
		//validar todas las posibilidades de si el aeropuerto
		//origen es igual al de destino, etc
	}

	@Test
	void vueloChequearQueLosPendientesNoTenganVuelosViejos(){

	}

	@Test
	void vueloVerificarCapacidadMaxima(){

	}

	@Test
	void vueloVerificarCapacidadMinima(){

	}

	@Test
	void aeropuertoTestBuscadorIATA(){

	}

	@Test //SIGUE DANDO OK, CUANDO DEBERIA DAR AEROPUERTOS IGUALES. SOLUCIONAR!!!!!
	void vueloValidarVueloMismoDestino(){
		Vuelo vuelo = new Vuelo();
		vuelo.setPrecio(new BigDecimal(100));
		vuelo.setEstadoVueloId(EstadoVueloEnum.GENERADO);
		vuelo.setAeropuertoOrigen(532);
		vuelo.setAeropuertoDestino(532);

		assertEquals(ValidacionVueloDataEnum.ERROR_AEROPUERTOS_IGUALES, vueloService.validar(vuelo));
	}

}
