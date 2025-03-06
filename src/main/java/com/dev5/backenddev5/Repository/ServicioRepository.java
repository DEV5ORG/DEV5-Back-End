package com.dev5.backenddev5.Repository;

import com.dev5.backenddev5.Model.Servicio;
import com.dev5.backenddev5.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
}
