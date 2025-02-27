package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Model.Evento;
import com.dev5.backenddev5.Model.Usuario;
import com.dev5.backenddev5.Repository.EventoRepository;
import com.dev5.backenddev5.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository; // Necesario para obtener el Usuario

    public EventoService(EventoRepository eventoRepository, UsuarioRepository usuarioRepository) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Metodo para construir y guardar un evento a partir de un request
    public Evento crearEvento(Evento request) {
        Evento evento = new Evento();
        evento.setFecha(request.getFecha());
        evento.setNumeroInvitados(request.getNumeroInvitados());
        evento.setPresupuesto(request.getPresupuesto());
        evento.setNombreEvento(request.getNombreEvento());
        evento.setUsuario_id(request.getUsuario_id());

        // Si hay información del usuario, podemos establecer la relación
        if (request.getUsuario_id() != null) {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(request.getUsuario_id().longValue());
            usuarioOptional.ifPresent(evento::setUsuarioEvento);
        }

        return eventoRepository.save(evento);
    }

    // Obtener todos los eventos
    public List<Evento> obtenerTodosLosEventos() {
        return eventoRepository.findAll();
    }

    // Obtener un evento por ID
    public Optional<Evento> obtenerEventoPorId(Long id) {
        return eventoRepository.findById(id);
    }

    // Actualizar un evento existente
    public Evento actualizarEvento(Long id, Evento request) {
        Optional<Evento> eventoExistente = eventoRepository.findById(id);

        if (eventoExistente.isPresent()) {
            Evento evento = eventoExistente.get();
            evento.setFecha(request.getFecha());
            evento.setNumeroInvitados(request.getNumeroInvitados());
            evento.setPresupuesto(request.getPresupuesto());
            evento.setNombreEvento(request.getNombreEvento());
            evento.setUsuario_id(request.getUsuario_id());

            // Actualizar la relación con el usuario si es necesario
            if (request.getUsuario_id() != null) {
                Optional<Usuario> usuarioOptional = usuarioRepository.findById(request.getUsuario_id().longValue());
                usuarioOptional.ifPresent(evento::setUsuarioEvento);
            }

            return eventoRepository.save(evento);
        }
        return null;
    }

    // Eliminar un evento por ID
    public void eliminarEvento(Long id) {
        eventoRepository.deleteById(id);
    }

    // Verificar si existe un evento por ID
    public boolean existeEventoPorId(Long id) {
        return eventoRepository.existsById(id);
    }
}