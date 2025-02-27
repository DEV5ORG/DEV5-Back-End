package com.dev5.backenddev5.Repository;

import com.dev5.backenddev5.Model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Métodos personalizados para buscar reservas
    List<Reserva> findByUsuarioId(Integer usuarioId);
    List<Reserva> findByEventoId(Integer eventoId);
    List<Reserva> findByEstadoReserva(String estadoReserva);
}
