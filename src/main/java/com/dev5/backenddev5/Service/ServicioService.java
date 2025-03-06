package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Model.Servicio;
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
        return servicioRepository.save(servicio);
    }

    public Servicio updateServicio(Integer id, Servicio servicioDetails) {
        Servicio servicio = servicioRepository.findById(id).orElseThrow(() -> new RuntimeException("Servicio not found"));
        validateUsuario(servicioDetails.getUsuario().getId());
        servicio.setTipoServicio(servicioDetails.getTipoServicio());
        servicio.setImagen(servicioDetails.getImagen());
        servicio.setUbicacion(servicioDetails.getUbicacion());
        servicio.setUsuario(servicioDetails.getUsuario());
        servicio.setItems(servicioDetails.getItems());
        servicio.setHorariosDeAtencion(servicioDetails.getHorariosDeAtencion());
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
}