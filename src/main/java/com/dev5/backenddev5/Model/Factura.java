package com.dev5.backenddev5.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "factura")
@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor

public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private Date fechaPago;

    @Column(nullable = false)
    private Integer idcuentaReceptora;

    @Column(nullable = false)
    private Integer idcuentaEmisora;

    @OneToOne
    @JoinColumn(name = "orden_id", nullable = false)  // Hibernate maneja esta columna
    private Orden orden;


    public Factura() {
    }

    public Factura(Integer id, Double monto, Date fechaPago, Integer idcuentaReceptora, Integer idcuentaEmisora, Orden orden) {
        this.id = id;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.idcuentaReceptora = idcuentaReceptora;
        this.idcuentaEmisora = idcuentaEmisora;
        this.orden = orden;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Integer getIdcuentaReceptora() {
        return idcuentaReceptora;
    }

    public void setIdcuentaReceptora(Integer idcuentaReceptora) {
        this.idcuentaReceptora = idcuentaReceptora;
    }

    public Integer getIdcuentaEmisora() {
        return idcuentaEmisora;
    }

    public void setIdcuentaEmisora(Integer idcuentaEmisora) {
        this.idcuentaEmisora = idcuentaEmisora;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }
}
