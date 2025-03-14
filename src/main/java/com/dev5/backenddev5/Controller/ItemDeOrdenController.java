package com.dev5.backenddev5.Controller;

import com.dev5.backenddev5.Model.ItemDeOrden;
import com.dev5.backenddev5.Service.ItemDeOrdenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itemsDeOrden")
public class ItemDeOrdenController {

    @Autowired
    private ItemDeOrdenService itemDeOrdenService;

    @GetMapping
    public List<ItemDeOrden> getAllItemsDeOrden() {
        return itemDeOrdenService.getAllItemsDeOrden();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDeOrden> getItemDeOrdenById(@PathVariable Integer id) {
        Optional<ItemDeOrden> itemDeOrden = itemDeOrdenService.getItemDeOrdenById(id);
        return itemDeOrden.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ItemDeOrden createItemDeOrden(@Valid @RequestBody ItemDeOrden itemDeOrden) {
        return itemDeOrdenService.createItemDeOrden(itemDeOrden);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDeOrden> updateItemDeOrden(@PathVariable Integer id, @RequestBody ItemDeOrden itemDeOrdenDetails) {
        ItemDeOrden updatedItemDeOrden = itemDeOrdenService.updateItemDeOrden(id, itemDeOrdenDetails);
        return ResponseEntity.ok(updatedItemDeOrden);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemDeOrden(@PathVariable Integer id) {
        itemDeOrdenService.deleteItemDeOrden(id);
        return ResponseEntity.noContent().build();
    }
}