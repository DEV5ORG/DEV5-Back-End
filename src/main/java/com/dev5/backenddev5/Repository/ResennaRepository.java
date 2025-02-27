package com.dev5.backenddev5.Repository;

import com.dev5.backenddev5.Model.Resenna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResennaRepository extends JpaRepository<Resenna, Long> {
    // Métodos personalizados para buscar reseñas
    List<Resenna> findByUsuarioId(Integer usuarioId);
    List<Resenna> findByProveedorId(Integer proveedorId);
    List<Resenna> findByEventoId(Integer eventoId);
}
