package com.dev5.backenddev5.Repository;

import com.dev5.backenddev5.Model.Orden;
import com.dev5.backenddev5.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<Orden, Integer> {
}
