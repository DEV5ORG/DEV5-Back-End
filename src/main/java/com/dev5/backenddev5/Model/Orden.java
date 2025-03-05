package com.dev5.backenddev5.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orden")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date fecha1;

    @Column(nullable = false)
    private Date fecha2;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)  // Hibernate maneja esta columna
    private Evento evento;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemDeOrden> itemsDeOrden = new ArrayList<>();

    @OneToOne(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private Factura factura;

    public Orden() {
    }

    public Orden(Integer id, Date fecha1, Date fecha2, Evento evento, List<ItemDeOrden> itemsDeOrden, Factura factura) {
        this.id = id;
        this.fecha1 = fecha1;
        this.fecha2 = fecha2;
        this.evento = evento;
        this.itemsDeOrden = itemsDeOrden;
        this.factura = factura;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<ItemDeOrden> getItemsDeOrden() {
        return itemsDeOrden;
    }

    public void setItemsDeOrden(List<ItemDeOrden> itemsDeOrden) {
        this.itemsDeOrden = itemsDeOrden;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
}
