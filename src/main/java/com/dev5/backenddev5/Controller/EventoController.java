package com.dev5.backenddev5.Controller;

import com.dev5.backenddev5.Model.Evento;
import com.dev5.backenddev5.Service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> getAllEventos() {
        return eventoService.getAllEventos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Integer id) {
        Optional<Evento> evento = eventoService.getEventoById(id);
        return evento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Evento createEvento(@Valid @RequestBody Evento evento) {
        return eventoService.createEvento(evento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Integer id, @Valid @RequestBody Evento eventoDetails) {
        Evento updatedEvento = eventoService.updateEvento(id, eventoDetails);
        return ResponseEntity.ok(updatedEvento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Integer id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }
    // Nuevo endpoint para obtener eventos por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Evento>> getEventosByUsuarioId(@PathVariable Integer usuarioId) {
        List<Evento> eventos = eventoService.getEventosByUsuarioId(usuarioId);
        // Si hay eventos, los devuelve con un status 200. Si no, devuelve un 404.
        return eventos.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(eventos);
    }
}
