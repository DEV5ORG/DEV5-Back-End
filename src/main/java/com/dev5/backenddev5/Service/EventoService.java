package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Model.Evento;
import com.dev5.backenddev5.Model.Usuario;
import com.dev5.backenddev5.Repository.EventoRepository;
import com.dev5.backenddev5.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> getEventoById(Integer id) {
        return eventoRepository.findById(id);
    }

    public Evento createEvento(Evento evento) {
        validateUsuario(evento.getUsuario().getId());
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(evento.getUsuario().getId());
        evento.setUsuario(usuarioOptional.get());
        return eventoRepository.save(evento);
    }

    public Evento updateEvento(Integer id, Evento eventoDetails) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento not found"));
        evento.setFechaHoraInicio(eventoDetails.getFechaHoraInicio());
        evento.setFechaHoraFinalizacion(eventoDetails.getFechaHoraFinalizacion());
        evento.setNumeroInvitados(eventoDetails.getNumeroInvitados());
        evento.setPresupuestoFinal(eventoDetails.getPresupuestoFinal());
        evento.setNombreEvento(eventoDetails.getNombreEvento());
        evento.setUbicacion(eventoDetails.getUbicacion());
        evento.setImagen(eventoDetails.getImagen());
        evento.setEditable(eventoDetails.getEditable());
        return eventoRepository.save(evento);
    }

    public void deleteEvento(Integer id) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento not found"));
        eventoRepository.delete(evento);
    }

    private void validateUsuario(Integer usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuario not found");
        }
    }

    // Metodo para obtener eventos por el id del usuario
    public List<Evento> getEventosByUsuarioId(Integer usuarioId) {
        return eventoRepository.findByUsuarioId(usuarioId);
    }
}
