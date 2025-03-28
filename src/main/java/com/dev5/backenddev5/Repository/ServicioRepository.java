package com.dev5.backenddev5.Repository;

import com.dev5.backenddev5.Model.Servicio;
import com.dev5.backenddev5.Model.TipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {



    @Query("SELECT s.id, s.nombre, s.descripcion, s.tipoServicio, s.imagen, s.ubicacion, s.horariosDeAtencion " +
            "FROM Servicio s")
    List<Object[]> findAllWithoutItems();

    @Query("SELECT s.id, s.tipoServicio, s.imagen, s.ubicacion FROM Servicio s WHERE s.id = :id")
    Optional<Object[]> findServicioByIdWithoutItems(@Param("id") Integer id);

    @Query("SELECT s FROM Servicio s LEFT JOIN FETCH s.horariosDeAtencion")
    List<Servicio> findAllWithoutItemsJoin();


    // Consulta personalizada para obtener servicios por tipo 'Lugares'
    @Query("SELECT s FROM Servicio s WHERE s.tipoServicio = 'Lugares'")
    List<Servicio> findByTipoServicio();

}
