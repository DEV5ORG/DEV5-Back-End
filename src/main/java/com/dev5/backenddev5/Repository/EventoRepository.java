package com.dev5.backenddev5.Repository;

import com.dev5.backenddev5.Model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    
}
