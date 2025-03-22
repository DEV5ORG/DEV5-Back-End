package com.dev5.backenddev5.Controller;

import com.dev5.backenddev5.Model.Servicio;
import com.dev5.backenddev5.Service.ServicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public List<Servicio> getAllServicios() {
        return servicioService.getAllServicios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable Integer id) {
        Optional<Servicio> servicio = servicioService.getServicioById(id);
        return servicio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Servicio createServicio(@Valid @RequestBody Servicio servicio) {
        return servicioService.createServicio(servicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> updateServicio(@PathVariable Integer id, @RequestBody Servicio servicioDetails) {
        Servicio updatedServicio = servicioService.updateServicio(id, servicioDetails);
        return ResponseEntity.ok(updatedServicio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable Integer id) {
        servicioService.deleteServicio(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sin-items")
    public List<Servicio> getAllServiciosWithoutItems() {
        return servicioService.getServiciosSinItems();
    }


}