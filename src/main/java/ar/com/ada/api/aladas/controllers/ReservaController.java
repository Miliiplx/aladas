package ar.com.ada.api.aladas.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.aladas.entities.Usuario;
import ar.com.ada.api.aladas.models.request.*;
import ar.com.ada.api.aladas.models.response.GenericResponse;
import ar.com.ada.api.aladas.services.*;

@RestController
public class ReservaController {

    @Autowired
    ReservaService service;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/api/reservas")
    public ResponseEntity<GenericResponse> generarReserva(@RequestBody InfoReservaNueva infoReserva) {

        GenericResponse respuesta = new GenericResponse();

        //obtengo quien esta del otro lado
        Authentication authenticateaction = SecurityContextHolder.getContext().getAuthentication();

        //obtengo el username de lo que esta autenticando
        String username = authenticateaction.getName();

        //busco el usuario por username
        Usuario usuario = usuarioService.buscarPorUsername(username);

        //con el usuario, obtengo el pasajero y con eso el Id del pasajero
        Integer numeroReserva = service.generarReserva(infoReserva.vueloId, usuario.getPasajero().getPasajeroId());

        respuesta.id = numeroReserva;
        respuesta.isOk = true;
        respuesta.message = "Reserva generada con éxito";

        return ResponseEntity.ok(respuesta);

    }

}
