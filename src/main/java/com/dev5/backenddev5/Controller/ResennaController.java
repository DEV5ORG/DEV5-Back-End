package com.dev5.backenddev5.Controller;

import com.dev5.backenddev5.Model.Resenna;
import com.dev5.backenddev5.Service.ResennaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resennas")
@CrossOrigin(origins = "*")
public class ResennaController {

    private final ResennaService resennaService;

    public ResennaController(ResennaService resennaService) {
        this.resennaService = resennaService;
    }

    // Crear una nueva reseña
    @PostMapping
    public ResponseEntity<Resenna> crearResenna(@RequestBody Resenna resenna) {
        Resenna nuevaResenna = resennaService.crearResenna(resenna);
        return new ResponseEntity<>(nuevaResenna, HttpStatus.CREATED);
    }

    // Obtener todas las reseñas
    @GetMapping
    public ResponseEntity<List<Resenna>> obtenerTodasLasResennas() {
        List<Resenna> resennas = resennaService.obtenerTodasLasResennas();
        return new ResponseEntity<>(resennas, HttpStatus.OK);
    }

    // Obtener una reseña por ID
    @GetMapping("/{id}")
    public ResponseEntity<Resenna> obtenerResennaPorId(@PathVariable Long id) {
        Optional<Resenna> resenna = resennaService.obtenerResennaPorId(id);
        return resenna.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar una reseña por ID
    @PutMapping("/{id}")
    public ResponseEntity<Resenna> actualizarResenna(@PathVariable Long id, @RequestBody Resenna resenna) {
        Resenna resennaActualizada = resennaService.actualizarResenna(id, resenna);
        if (resennaActualizada != null) {
            return new ResponseEntity<>(resennaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una reseña por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResenna(@PathVariable Long id) {
        if (resennaService.existeResennaPorId(id)) {
            resennaService.eliminarResenna(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Buscar reseñas por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Resenna>> buscarResennasPorUsuario(@PathVariable Integer usuarioId) {
        List<Resenna> resennas = resennaService.buscarResennasPorUsuarioId(usuarioId);
        return new ResponseEntity<>(resennas, HttpStatus.OK);
    }

    // Buscar reseñas por proveedor
    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<Resenna>> buscarResennasPorProveedor(@PathVariable Integer proveedorId) {
        List<Resenna> resennas = resennaService.buscarResennasPorProveedorId(proveedorId);
        return new ResponseEntity<>(resennas, HttpStatus.OK);
    }

    // Buscar reseñas por evento
    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<Resenna>> buscarResennasPorEvento(@PathVariable Integer eventoId) {
        List<Resenna> resennas = resennaService.buscarResennasPorEventoId(eventoId);
        return new ResponseEntity<>(resennas, HttpStatus.OK);
    }

    // Calcular calificación promedio para un proveedor
    @GetMapping("/proveedor/{proveedorId}/calificacion")
    public ResponseEntity<Double> calcularCalificacionPromedioPorProveedor(@PathVariable Integer proveedorId) {
        Double calificacionPromedio = resennaService.calcularCalificacionPromedioPorProveedor(proveedorId);
        return new ResponseEntity<>(calificacionPromedio, HttpStatus.OK);
    }
}