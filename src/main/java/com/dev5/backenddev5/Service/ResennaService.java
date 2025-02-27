package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Model.Resenna;
import com.dev5.backenddev5.Repository.ResennaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResennaService {

    private final ResennaRepository resennaRepository;

    public ResennaService(ResennaRepository resennaRepository) {
        this.resennaRepository = resennaRepository;
    }

    // Metodo para construir y guardar una reseña a partir de un request
    public Resenna crearResenna(Resenna request) {
        Resenna resenna = new Resenna();
        resenna.setUsuarioId(request.getUsuarioId());
        resenna.setProveedorId(request.getProveedorId());
        resenna.setEventoId(request.getEventoId());
        resenna.setCalificacion(request.getCalificacion());
        resenna.setComentario(request.getComentario());

        return resennaRepository.save(resenna);
    }

    // Obtener todas las reseñas
    public List<Resenna> obtenerTodasLasResennas() {
        return resennaRepository.findAll();
    }

    // Obtener una reseña por ID
    public Optional<Resenna> obtenerResennaPorId(Long id) {
        return resennaRepository.findById(id);
    }

    // Actualizar una reseña existente
    public Resenna actualizarResenna(Long id, Resenna request) {
        Optional<Resenna> resennaExistente = resennaRepository.findById(id);

        if (resennaExistente.isPresent()) {
            Resenna resenna = resennaExistente.get();
            resenna.setUsuarioId(request.getUsuarioId());
            resenna.setProveedorId(request.getProveedorId());
            resenna.setEventoId(request.getEventoId());
            resenna.setCalificacion(request.getCalificacion());
            resenna.setComentario(request.getComentario());

            return resennaRepository.save(resenna);
        }
        return null; // O podrías lanzar una excepción si la reseña no existe
    }

    // Eliminar una reseña por ID
    public void eliminarResenna(Long id) {
        resennaRepository.deleteById(id);
    }

    // Verificar si existe una reseña por ID
    public boolean existeResennaPorId(Long id) {
        return resennaRepository.existsById(id);
    }

    // Metodos adicionales para buscar reseñas por relaciones

    // Buscar reseñas por usuario
    public List<Resenna> buscarResennasPorUsuarioId(Integer usuarioId) {
        return resennaRepository.findByUsuarioId(usuarioId);
    }

    // Buscar reseñas por proveedor
    public List<Resenna> buscarResennasPorProveedorId(Integer proveedorId) {
        return resennaRepository.findByProveedorId(proveedorId);
    }

    // Buscar reseñas por evento
    public List<Resenna> buscarResennasPorEventoId(Integer eventoId) {
        return resennaRepository.findByEventoId(eventoId);
    }

    // Calcular calificación promedio para un proveedor
    public Double calcularCalificacionPromedioPorProveedor(Integer proveedorId) {
        List<Resenna> resennas = resennaRepository.findByProveedorId(proveedorId);
        if (resennas.isEmpty()) {
            return 0.0;
        }

        int suma = 0;
        for (Resenna resenna : resennas) {
            suma += resenna.getCalificacion();
        }

        return (double) suma / resennas.size();
    }
}