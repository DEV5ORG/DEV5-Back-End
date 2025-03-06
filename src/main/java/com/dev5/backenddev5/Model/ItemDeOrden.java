package com.dev5.backenddev5.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "itemDeOrden")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDeOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Double precioTotal;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public ItemDeOrden() {
    }

    public ItemDeOrden(Integer id, Integer cantidad, Double precioTotal, Orden orden, Item item) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.orden = orden;
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
