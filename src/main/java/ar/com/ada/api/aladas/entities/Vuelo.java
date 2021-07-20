package ar.com.ada.api.aladas.entities;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "vuelo")
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vuelo_id")
    private Integer vueloId;

    private Date fecha;

    @Column(name = "estado_vuelo_id")
    private Integer estadoVueloId;

    private Integer capacidad;

    @Column(name = "aeropuerto_origen")
    private Integer aeropuertoOrigen;

    @Column(name = "aeropuerto_destino")
    private Integer aeropuertoDestino;

    private BigDecimal precio;

    @Column(name = "codigo_moneda")
    private String codigoMoneda;

    @OneToMany(mappedBy = "vuelo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Reserva> reservas = new ArrayList<>();

    public Integer getVueloId() {
        return vueloId;
    }

    public void setVueloId(Integer vueloId) {
        this.vueloId = vueloId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getEstadoVueloId() {
        return estadoVueloId;
    }

    public void setEstadoVueloId(Integer estadoVueloId) {
        this.estadoVueloId = estadoVueloId;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    public void setAeropuertoOrigen(Integer aeropuertoOrigen) {
        this.aeropuertoOrigen = aeropuertoOrigen;
    }

    public Integer getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    public void setAeropuertoDestino(Integer aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(String codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////
    public void agregarReserva(Reserva reserva) {
        this.reservas.add(reserva);
        reserva.setVuelo(this);
    }

}
