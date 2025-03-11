package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Model.HorariosAtencion;
import com.dev5.backenddev5.Model.Servicio;
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

    public List<HorariosAtencion> getAllHorariosAtencion() {
        return horariosAtencionRepository.findAll();
    }

    public Optional<HorariosAtencion> getHorariosAtencionById(Integer id) {
        return horariosAtencionRepository.findById(id);
    }

    public HorariosAtencion createHorariosAtencion(HorariosAtencion horariosAtencion) {
        validateServicio(horariosAtencion.getServicio().getId());
        Optional<Servicio> servicioOptional = servicioRepository.findById(horariosAtencion.getServicio().getId());
        horariosAtencion.setServicio(servicioOptional.get());
        horariosAtencion.setServicio_id(servicioOptional.get().getId());
        return horariosAtencionRepository.save(horariosAtencion);
    }

    public HorariosAtencion updateHorariosAtencion(Integer id, HorariosAtencion horariosAtencionDetails) {
        HorariosAtencion horariosAtencion = horariosAtencionRepository.findById(id).orElseThrow(() -> new RuntimeException("HorariosAtencion not found"));
        horariosAtencion.setInicio(horariosAtencionDetails.getInicio());
        horariosAtencion.setFin(horariosAtencionDetails.getFin());
        horariosAtencion.setHoraInicioDia(horariosAtencionDetails.getHoraInicioDia());
        horariosAtencion.setHoraFinDia(horariosAtencionDetails.getHoraFinDia());
        return horariosAtencionRepository.save(horariosAtencion);
    }

    public void deleteHorariosAtencion(Integer id) {
        HorariosAtencion horariosAtencion = horariosAtencionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HorariosAtencion not found"));
        horariosAtencionRepository.delete(horariosAtencion);
    }

    private void validateServicio(Integer servicioId) {
        if (!servicioRepository.existsById(servicioId)) {
            throw new RuntimeException("Servicio not found");
        }
    }
}
