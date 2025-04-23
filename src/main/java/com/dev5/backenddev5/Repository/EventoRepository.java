package com.dev5.backenddev5.Repository;

import com.dev5.backenddev5.Model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

    List<Evento> findByUsuarioId(Integer usuarioId);

    long countByUsuarioId(Integer usuarioId);

}
