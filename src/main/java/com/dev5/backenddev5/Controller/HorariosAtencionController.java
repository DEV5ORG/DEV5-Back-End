package com.dev5.backenddev5.Controller;

import com.dev5.backenddev5.Model.HorariosAtencion;
import com.dev5.backenddev5.Service.HorariosAtencionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/horariosAtencion")
public class HorariosAtencionController {

    @Autowired
    private HorariosAtencionService service;

    @GetMapping
    public List<HorariosAtencion> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorariosAtencion> getById(@PathVariable Integer id) {
        Optional<HorariosAtencion> horariosAtencion = service.findById(id);
        return horariosAtencion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public HorariosAtencion create(@RequestBody HorariosAtencion horariosAtencion) {
        return service.save(horariosAtencion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorariosAtencion> update(@PathVariable Integer id, @RequestBody HorariosAtencion horariosAtencion) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        horariosAtencion.setId(id);
        return ResponseEntity.ok(service.update(horariosAtencion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
