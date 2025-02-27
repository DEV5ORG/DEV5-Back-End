package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Model.Reserva;
import com.dev5.backenddev5.Repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    // Metodo para construir y guardar una reserva a partir de un request
    public Reserva crearReserva(Reserva request) {
        Reserva reserva = new Reserva();
        reserva.setEventoId(request.getEventoId());
        reserva.setUsuarioId(request.getUsuarioId());
        reserva.setFechaReserva(request.getFechaReserva());
        reserva.setEstadoReserva(request.getEstadoReserva());

        return reservaRepository.save(reserva);
    }

    // Obtener todas las reservas
    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.findAll();
    }

    // Obtener una reserva por ID
    public Optional<Reserva> obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id);
    }

    // Actualizar una reserva existente
    public Reserva actualizarReserva(Long id, Reserva request) {
        Optional<Reserva> reservaExistente = reservaRepository.findById(id);

        if (reservaExistente.isPresent()) {
            Reserva reserva = reservaExistente.get();
            reserva.setEventoId(request.getEventoId());
            reserva.setUsuarioId(request.getUsuarioId());
            reserva.setFechaReserva(request.getFechaReserva());
            reserva.setEstadoReserva(request.getEstadoReserva());

            return reservaRepository.save(reserva);
        }
        return null;
    }

    // Eliminar una reserva por ID
    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    // Verificar si existe una reserva por ID
    public boolean existeReservaPorId(Long id) {
        return reservaRepository.existsById(id);
    }

    // Metodos adicionales para buscar reservas por relaciones

    // Buscar reservas por usuario
    public List<Reserva> buscarReservasPorUsuarioId(Integer usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    // Buscar reservas por evento
    public List<Reserva> buscarReservasPorEventoId(Integer eventoId) {
        return reservaRepository.findByEventoId(eventoId);
    }

    // Buscar reservas por estado
    public List<Reserva> buscarReservasPorEstado(String estadoReserva) {
        return reservaRepository.findByEstadoReserva(estadoReserva);
    }

    // Actualizar estado de una reserva
    public Reserva actualizarEstadoReserva(Long id, String nuevoEstado) {
        Optional<Reserva> reservaExistente = reservaRepository.findById(id);

        if (reservaExistente.isPresent()) {
            Reserva reserva = reservaExistente.get();
            reserva.setEstadoReserva(nuevoEstado);

            return reservaRepository.save(reserva);
        }
        return null;
    }
}