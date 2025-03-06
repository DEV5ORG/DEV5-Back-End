package com.dev5.backenddev5.Controller;

import com.dev5.backenddev5.Model.Orden;
import com.dev5.backenddev5.Service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public List<Orden> getAllOrdenes() {
        return ordenService.getAllOrdenes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> getOrdenById(@PathVariable Integer id) {
        Optional<Orden> orden = ordenService.getOrdenById(id);
        return orden.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Orden createOrden(@RequestBody Orden orden) {
        return ordenService.createOrden(orden);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orden> updateOrden(@PathVariable Integer id, @RequestBody Orden ordenDetails) {
        Orden updatedOrden = ordenService.updateOrden(id, ordenDetails);
        return ResponseEntity.ok(updatedOrden);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrden(@PathVariable Integer id) {
        ordenService.deleteOrden(id);
        return ResponseEntity.noContent().build();
    }
}