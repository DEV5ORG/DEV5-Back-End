package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Model.Item;
import com.dev5.backenddev5.Model.Servicio;
import com.dev5.backenddev5.Repository.ItemRepository;
import com.dev5.backenddev5.Repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(Integer id) {
        return itemRepository.findById(id);
    }

    public Item createItem(Item item) {
        validateServicio(item.getServicio().getId());
        Optional<Servicio> servicioOptional = servicioRepository.findById(item.getServicio().getId());
        item.setServicio(servicioOptional.get());
        return itemRepository.save(item);
    }

    public Item updateItem(Integer id, Item itemDetails) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        item.setNombre(itemDetails.getNombre());
        item.setDescripcion(itemDetails.getDescripcion());
        item.setPrecio(itemDetails.getPrecio());
        item.setImagen(itemDetails.getImagen());
        item.setUbicacion(itemDetails.getUbicacion());
        return itemRepository.save(item);
    }

    public void deleteItem(Integer id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        itemRepository.delete(item);
    }

    private void validateServicio(Integer servicioId) {
        if (!servicioRepository.existsById(servicioId)) {
            throw new RuntimeException("Servicio not found");
        }
    }
}