package com.dev5.backenddev5.Repository;

import com.dev5.backenddev5.Model.Usuario;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT p.nombre, p.correoElectronico  FROM Usuario p")
    List<Object[]> findUserData();

    Optional<Usuario> findByCorreoElectronico(String correoElectronico);
}
