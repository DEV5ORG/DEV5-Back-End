package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Model.ItemDeOrden;
import com.dev5.backenddev5.Model.Orden;
import com.dev5.backenddev5.Model.Item;
import com.dev5.backenddev5.Repository.ItemDeOrdenRepository;
import com.dev5.backenddev5.Repository.OrdenRepository;
import com.dev5.backenddev5.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemDeOrdenService {

    @Autowired
    private ItemDeOrdenRepository itemDeOrdenRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<ItemDeOrden> getAllItemsDeOrden() {
        return itemDeOrdenRepository.findAll();
    }

    public Optional<ItemDeOrden> getItemDeOrdenById(Integer id) {
        return itemDeOrdenRepository.findById(id);
    }

    public ItemDeOrden createItemDeOrden(ItemDeOrden itemDeOrden) {
        validateOrden(itemDeOrden.getOrden().getId());
        validateItem(itemDeOrden.getItem().getId());
        return itemDeOrdenRepository.save(itemDeOrden);
    }

    public ItemDeOrden updateItemDeOrden(Integer id, ItemDeOrden itemDeOrdenDetails) {
        ItemDeOrden itemDeOrden = itemDeOrdenRepository.findById(id).orElseThrow(() -> new RuntimeException("ItemDeOrden not found"));
        validateOrden(itemDeOrdenDetails.getOrden().getId());
        validateItem(itemDeOrdenDetails.getItem().getId());
        itemDeOrden.setCantidad(itemDeOrdenDetails.getCantidad());
        itemDeOrden.setPrecioTotal(itemDeOrdenDetails.getPrecioTotal());
        itemDeOrden.setOrden(itemDeOrdenDetails.getOrden());
        itemDeOrden.setItem(itemDeOrdenDetails.getItem());
        return itemDeOrdenRepository.save(itemDeOrden);
    }

    public void deleteItemDeOrden(Integer id) {
        ItemDeOrden itemDeOrden = itemDeOrdenRepository.findById(id).orElseThrow(() -> new RuntimeException("ItemDeOrden not found"));
        itemDeOrdenRepository.delete(itemDeOrden);
    }

    private void validateOrden(Integer ordenId) {
        if (!ordenRepository.existsById(ordenId)) {
            throw new RuntimeException("Orden not found");
        }
    }

    private void validateItem(Integer itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new RuntimeException("Item not found");
        }
    }
}