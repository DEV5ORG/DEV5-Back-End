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

        // Crear la orden sin factura
        orden.setFactura(null);
        Orden nuevaOrden = ordenRepository.save(orden);

        // Crear y asociar la factura después de que la orden tenga un ID
        Factura factura = new Factura();
        factura.setOrden(nuevaOrden);
        Factura nuevaFactura = facturaRepository.save(factura);

        // Asociar la factura a la orden y guardar de nuevo
        nuevaOrden.setFactura(nuevaFactura);
        return ordenRepository.save(nuevaOrden);
    }


    public Orden updateOrden(Integer id, Orden ordenDetails) {
        Orden orden = ordenRepository.findById(id).orElseThrow(() -> new RuntimeException("Orden not found"));
        validateEvento(ordenDetails.getEvento().getId());
        orden.setFecha1(ordenDetails.getFecha1());
        orden.setFecha2(ordenDetails.getFecha2());
        orden.setEvento(ordenDetails.getEvento());
        orden.setItemsDeOrden(ordenDetails.getItemsDeOrden());
        orden.setFactura(ordenDetails.getFactura());
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