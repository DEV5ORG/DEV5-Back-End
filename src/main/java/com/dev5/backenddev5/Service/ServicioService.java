package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Model.Servicio;
import com.dev5.backenddev5.Model.TipoServicio;
import com.dev5.backenddev5.Model.Usuario;
import com.dev5.backenddev5.Repository.ServicioRepository;
import com.dev5.backenddev5.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Servicio> getAllServicios() {
        return servicioRepository.findAll();
    }

    public Optional<Servicio> getServicioById(Integer id) {
        return servicioRepository.findById(id);
    }

    public Servicio createServicio(Servicio servicio) {
        validateUsuario(servicio.getUsuario().getId());
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(servicio.getUsuario().getId());
        servicio.setUsuario(usuarioOptional.get());
        return servicioRepository.save(servicio);
    }

    public Servicio updateServicio(Integer id, Servicio servicioDetails) {
        Servicio servicio = servicioRepository.findById(id).orElseThrow(() -> new RuntimeException("Servicio not found"));
        servicio.setTipoServicio(servicioDetails.getTipoServicio());
        servicio.setImagen(servicioDetails.getImagen());
        servicio.setUbicacion(servicioDetails.getUbicacion());
        return servicioRepository.save(servicio);
    }

    public void deleteServicio(Integer id) {
        Servicio servicio = servicioRepository.findById(id).orElseThrow(() -> new RuntimeException("Servicio not found"));
        servicioRepository.delete(servicio);
    }

    private void validateUsuario(Integer usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuario not found");
        }
    }

    public List<Servicio> getServiciosSinItems() {
        return servicioRepository.findAllWithoutItemsJoin();
    }
    // Metodo para obtener los servicios de tipo 'Lugares'
    public List<Servicio> getServiciosByTipoLugares() {
        return servicioRepository.findByTipoServicio();

    }


    // Metodo para obtener todos los servicios con los campos requeridos y los horarios de atención
    public List<Object[]> getAllServiciosWithRequiredFields() {
        return servicioRepository.findAllWithRequiredFields();
    }
}