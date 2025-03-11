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
    private HorariosAtencionService horariosAtencionService;

    @GetMapping
    public List<HorariosAtencion> getAllHorariosAtencion() {
        return horariosAtencionService.getAllHorariosAtencion();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorariosAtencion> getHorariosAtencionById(@PathVariable Integer id) {
        Optional<HorariosAtencion> horariosAtencion = horariosAtencionService.getHorariosAtencionById(id);
        return horariosAtencion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public HorariosAtencion createHorariosAtencion(@RequestBody HorariosAtencion horariosAtencion) {
        return horariosAtencionService.createHorariosAtencion(horariosAtencion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorariosAtencion> updateHorariosAtencion(@PathVariable Integer id, @RequestBody HorariosAtencion horariosAtencionDetails) {
        HorariosAtencion updatedHorariosAtencion = horariosAtencionService.updateHorariosAtencion(id, horariosAtencionDetails);
        return ResponseEntity.ok(updatedHorariosAtencion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorariosAtencion(@PathVariable Integer id) {
        horariosAtencionService.deleteHorariosAtencion(id);
        return ResponseEntity.noContent().build();
    }
}
