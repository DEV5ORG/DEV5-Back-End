package com.dev5.backenddev5.Controller;

import com.dev5.backenddev5.Model.Reserva;
import com.dev5.backenddev5.Service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    // Crear una nueva reserva
    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@RequestBody Reserva reserva) {
        Reserva nuevaReserva = reservaService.crearReserva(reserva);
        return new ResponseEntity<>(nuevaReserva, HttpStatus.CREATED);
    }

    // Obtener todas las reservas
    @GetMapping
    public ResponseEntity<List<Reserva>> obtenerTodasLasReservas() {
        List<Reserva> reservas = reservaService.obtenerTodasLasReservas();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    // Obtener una reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerReservaPorId(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.obtenerReservaPorId(id);
        return reserva.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar una reserva
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizarReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        if (!reservaService.existeReservaPorId(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Reserva reservaActualizada = reservaService.actualizarReserva(id, reserva);
        return new ResponseEntity<>(reservaActualizada, HttpStatus.OK);
    }

    // Eliminar una reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        if (!reservaService.existeReservaPorId(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reservaService.eliminarReserva(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Actualizar solo el estado de una reserva
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Reserva> actualizarEstadoReserva(@PathVariable Long id, @RequestBody String nuevoEstado) {
        if (!reservaService.existeReservaPorId(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Reserva reservaActualizada = reservaService.actualizarEstadoReserva(id, nuevoEstado);
        return new ResponseEntity<>(reservaActualizada, HttpStatus.OK);
    }

    // Buscar reservas por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Reserva>> buscarReservasPorUsuario(@PathVariable Integer usuarioId) {
        List<Reserva> reservas = reservaService.buscarReservasPorUsuarioId(usuarioId);
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    // Buscar reservas por evento
    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<Reserva>> buscarReservasPorEvento(@PathVariable Integer eventoId) {
        List<Reserva> reservas = reservaService.buscarReservasPorEventoId(eventoId);
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    // Buscar reservas por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Reserva>> buscarReservasPorEstado(@PathVariable String estado) {
        List<Reserva> reservas = reservaService.buscarReservasPorEstado(estado);
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }
}