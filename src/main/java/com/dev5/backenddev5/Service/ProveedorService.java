package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Model.Proveedor;
import com.dev5.backenddev5.Repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    // Método para construir y guardar un proveedor a partir de un request
    public Proveedor crearProveedor(Proveedor request) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(request.getNombre());
        proveedor.setTipoServicio(request.getTipoServicio());
        proveedor.setContacto(request.getContacto());
        proveedor.setCalificacion(request.getCalificacion());

        return proveedorRepository.save(proveedor);
    }

    // Obtener todos los proveedores
    public List<Proveedor> obtenerTodosLosProveedores() {
        return proveedorRepository.findAll();
    }

    // Obtener un proveedor por ID
    public Optional<Proveedor> obtenerProveedorPorId(Long id) {
        return proveedorRepository.findById(id);
    }

    // Actualizar un proveedor existente
    public Proveedor actualizarProveedor(Long id, Proveedor request) {
        Optional<Proveedor> proveedorExistente = proveedorRepository.findById(id);

        if (proveedorExistente.isPresent()) {
            Proveedor proveedor = proveedorExistente.get();
            proveedor.setNombre(request.getNombre());
            proveedor.setTipoServicio(request.getTipoServicio());
            proveedor.setContacto(request.getContacto());
            proveedor.setCalificacion(request.getCalificacion());

            return proveedorRepository.save(proveedor);
        }
        return null;
    }

    // Eliminar un proveedor por ID
    public void eliminarProveedor(Long id) {
        proveedorRepository.deleteById(id);
    }

    // Verificar si existe un proveedor por ID
    public boolean existeProveedorPorId(Long id) {
        return proveedorRepository.existsById(id);
    }

    // Metodo adicional para buscar proveedores por tipo de servicio
    public List<Proveedor> buscarProveedoresPorTipoServicio(String tipoServicio) {
        return proveedorRepository.findByTipoServicio(tipoServicio);
    }
}