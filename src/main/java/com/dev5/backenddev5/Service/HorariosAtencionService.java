package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Model.HorariosAtencion;
import com.dev5.backenddev5.Repository.HorarioAtencionRepository;
import com.dev5.backenddev5.Repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorariosAtencionService {

    @Autowired
    private HorarioAtencionRepository horariosAtencionRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    public List<HorariosAtencion> findAll() {
        return horariosAtencionRepository.findAll();
    }

    public Optional<HorariosAtencion> findById(Integer id) {
        return horariosAtencionRepository.findById(id);
    }

    public HorariosAtencion save(HorariosAtencion horariosAtencion) {
        validateServicio(horariosAtencion.getServicio_id());
        return horariosAtencionRepository.save(horariosAtencion);
    }

    public HorariosAtencion update(HorariosAtencion horariosAtencion) {
        validateServicio(horariosAtencion.getServicio_id());
        return horariosAtencionRepository.save(horariosAtencion);
    }

    public void deleteById(Integer id) {
        horariosAtencionRepository.deleteById(id);
    }

    private void validateServicio(Integer servicioId) {
        if (!servicioRepository.existsById(servicioId)) {
            throw new RuntimeException("Servicio not found");
        }
    }
}
