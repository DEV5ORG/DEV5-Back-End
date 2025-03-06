package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Model.Factura;
import com.dev5.backenddev5.Model.Orden;
import com.dev5.backenddev5.Repository.FacturaRepository;
import com.dev5.backenddev5.Repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    public List<Factura> getAllFacturas() {
        return facturaRepository.findAll();
    }

    public Optional<Factura> getFacturaById(Integer id) {
        return facturaRepository.findById(id);
    }

    public Factura createFactura(Factura factura) {
        validateOrden(factura.getOrden().getId());
        return facturaRepository.save(factura);
    }

    public Factura updateFactura(Integer id, Factura facturaDetails) {
        Factura factura = facturaRepository.findById(id).orElseThrow(() -> new RuntimeException("Factura not found"));
        validateOrden(facturaDetails.getOrden().getId());
        factura.setMonto(facturaDetails.getMonto());
        factura.setFechaPago(facturaDetails.getFechaPago());
        factura.setIdcuentaReceptora(facturaDetails.getIdcuentaReceptora());
        factura.setIdcuentaEmisora(facturaDetails.getIdcuentaEmisora());
        factura.setOrden(facturaDetails.getOrden());
        return facturaRepository.save(factura);
    }

    public void deleteFactura(Integer id) {
        Factura factura = facturaRepository.findById(id).orElseThrow(() -> new RuntimeException("Factura not found"));
        facturaRepository.delete(factura);
    }

    private void validateOrden(Integer ordenId) {
        if (!ordenRepository.existsById(ordenId)) {
            throw new RuntimeException("Orden not found");
        }
    }
}