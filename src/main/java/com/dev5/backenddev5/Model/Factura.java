package com.dev5.backenddev5.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "factura")
@Getter
@Setter
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El monto no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor que 0")
    @Column(nullable = false)
    private Double monto;

    @NotNull(message = "La fecha de pago no puede ser nula")
    @PastOrPresent(message = "La fecha de pago debe ser en el pasado o presente")
    @Column(nullable = false)
    private Date fechaPago;

    @NotNull(message = "El ID de la cuenta receptora no puede ser nulo")
    @Column(nullable = false)
    private Integer idcuentaReceptora;

    @NotNull(message = "El ID de la cuenta emisora no puede ser nulo")
    @Column(nullable = false)
    private Integer idcuentaEmisora;

    @OneToOne
    @JoinColumn(name = "orden_id", nullable = false)
    @JsonBackReference
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
