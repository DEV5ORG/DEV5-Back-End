package com.dev5.backenddev5.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "eventos")
@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor

public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date fechaHoraInicio;

    @Column(nullable = false)
    private Date fechaHoraFinalizacion;

    @Column(nullable = false)
    private Integer numeroInvitados;

    @Column(nullable = false)
    private Double presupuestoFinal;

    @Column(nullable = false)
    private String nombreEvento;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private String imagen;

    @Column(nullable = false)
    private Boolean editable;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orden> ordenes = new ArrayList<>();

    //Constructor
    public Evento() {
    }

    public Evento(Integer id, Date fechaHoraInicio, Date fechaHoraFinalizacion, Integer numeroInvitados, Double presupuestoFinal, String nombreEvento, String ubicacion, String imagen, Boolean editable, Usuario usuario, List<Orden> ordenes) {
        this.id = id;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFinalizacion = fechaHoraFinalizacion;
        this.numeroInvitados = numeroInvitados;
        this.presupuestoFinal = presupuestoFinal;
        this.nombreEvento = nombreEvento;
        this.ubicacion = ubicacion;
        this.imagen = imagen;
        this.editable = editable;
        this.usuario = usuario;
        this.ordenes = ordenes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFinalizacion() {
        return fechaHoraFinalizacion;
    }

    public void setFechaHoraFinalizacion(Date fechaHoraFinalizacion) {
        this.fechaHoraFinalizacion = fechaHoraFinalizacion;
    }

    public Integer getNumeroInvitados() {
        return numeroInvitados;
    }

    public void setNumeroInvitados(Integer numeroInvitados) {
        this.numeroInvitados = numeroInvitados;
    }

    public Double getPresupuestoFinal() {
        return presupuestoFinal;
    }

    public void setPresupuestoFinal(Double presupuestoFinal) {
        this.presupuestoFinal = presupuestoFinal;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }


    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }
}
