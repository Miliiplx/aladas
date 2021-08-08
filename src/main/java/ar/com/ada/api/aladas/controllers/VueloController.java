package ar.com.ada.api.aladas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.aladas.entities.Vuelo;
import ar.com.ada.api.aladas.models.request.EstadoVueloRequest;
import ar.com.ada.api.aladas.models.response.GenericResponse;
import ar.com.ada.api.aladas.services.VueloService;
import ar.com.ada.api.aladas.services.VueloService.ValidacionVueloDataEnum;

@RestController
public class VueloController {

    @Autowired
    VueloService service;

    /*
     * ////OTRA FORMA DE HACERLO.//// private VueloService service;
     * 
     * public VueloController(VueloService service){ this.service = service; }
     */

    @PostMapping("/api/vuelos")
    public ResponseEntity<GenericResponse> crearVuelo(@RequestBody Vuelo vuelo) {
        GenericResponse respuesta = new GenericResponse();

        ValidacionVueloDataEnum resultadoValidacion = service.validar(vuelo);
        if (resultadoValidacion == ValidacionVueloDataEnum.OK) {
            service.crear(vuelo);

            respuesta.isOk = true;
            respuesta.id = vuelo.getVueloId();
            respuesta.message = "Vuelo creado correctamente.";

            return ResponseEntity.ok(respuesta);
        }

        else {
            respuesta.isOk = false;
            respuesta.message = "ERROR. (" + resultadoValidacion.toString() + ")";

            return ResponseEntity.badRequest().body(respuesta);
        }
    }

    @PutMapping("/api/vuelos/{id}/estados")
    public ResponseEntity<GenericResponse> actualizarEstadoVuelo(@PathVariable Integer id,
            @RequestBody EstadoVueloRequest estadoVuelo) {

        GenericResponse respuesta = new GenericResponse();
        respuesta.isOk = true;

        Vuelo vuelo = service.buscarPorId(id);

        vuelo.setEstadoVueloId(estadoVuelo.estado);

        service.guardar(vuelo);

        respuesta.message = "Estado actualizado con éxito.";

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/api/vuelos/abiertos")
    public ResponseEntity<List<Vuelo>> vuelosAbiertos(){
        
        return ResponseEntity.ok(service.traerVuelosAbiertos());
    }

}
