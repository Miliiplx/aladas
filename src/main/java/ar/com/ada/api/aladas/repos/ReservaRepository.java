package ar.com.ada.api.aladas.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.ada.api.aladas.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer>  {
    
}
