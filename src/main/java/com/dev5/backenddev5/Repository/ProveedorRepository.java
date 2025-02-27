package com.dev5.backenddev5.Repository;

import com.dev5.backenddev5.Model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    // Método personalizado para buscar por tipo de servicio
    List<Proveedor> findByTipoServicio(String tipoServicio);
}
