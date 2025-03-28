package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Model.Orden;
import com.dev5.backenddev5.Model.Evento;
import com.dev5.backenddev5.Model.ItemDeOrden;
import com.dev5.backenddev5.Model.Factura;
import com.dev5.backenddev5.Repository.OrdenRepository;
import com.dev5.backenddev5.Repository.EventoRepository;
import com.dev5.backenddev5.Repository.ItemDeOrdenRepository;
import com.dev5.backenddev5.Repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ItemDeOrdenRepository itemDeOrdenRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    public List<Orden> getAllOrdenes() {
        return ordenRepository.findAll();
    }

    public Optional<Orden> getOrdenById(Integer id) {
        return ordenRepository.findById(id);
    }

    public Orden createOrden(Orden orden) {
        validateEvento(orden.getEvento().getId());
        return ordenRepository.save(orden);
    }


    @Transactional
    public Orden updateOrden(Integer id, Orden ordenDetails) {
        // Buscar la orden existente
        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden not found"));

        // Actualizar fechas y evento
        orden.setFecha1(ordenDetails.getFecha1());
        orden.setFecha2(ordenDetails.getFecha2());

        // Manejar actualización de items de orden
        if (ordenDetails.getItemsDeOrden() != null && !ordenDetails.getItemsDeOrden().isEmpty()) {
            // Limpiar items de orden existentes
            orden.getItemsDeOrden().clear();

            // Recorrer y agregar nuevos items de orden
            for (ItemDeOrden itemDeOrden : ordenDetails.getItemsDeOrden()) {
                // Buscar el item de orden completo por su ID
                ItemDeOrden itemCompleto = itemDeOrdenRepository.findById(itemDeOrden.getId())
                        .orElseThrow(() -> new RuntimeException("Item de Orden not found with id: " + itemDeOrden.getId()));

                // Añadir item de orden a la orden
                orden.getItemsDeOrden().add(itemCompleto);
            }
        }

        // Manejar actualización de factura
        if (ordenDetails.getFactura() != null) {
            // Buscar la factura completa por su ID
            Factura facturaCompleta = facturaRepository.findById(ordenDetails.getFactura().getId())
                    .orElseThrow(() -> new RuntimeException("Factura not found with id: " + ordenDetails.getFactura().getId()));

            // Establecer la factura
            orden.setFactura(facturaCompleta);
        }

        // Guardar y retornar la orden actualizada
        return ordenRepository.save(orden);
    }

    public void deleteOrden(Integer id) {
        Orden orden = ordenRepository.findById(id).orElseThrow(() -> new RuntimeException("Orden not found"));
        ordenRepository.delete(orden);
    }

    private void validateEvento(Integer eventoId) {
        if (!eventoRepository.existsById(eventoId)) {
            throw new RuntimeException("Evento not found");
        }
    }
}