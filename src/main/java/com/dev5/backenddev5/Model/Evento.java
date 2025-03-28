package com.dev5.backenddev5.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "eventos")
@Getter
@Setter
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La fecha y hora de inicio no puede ser nula")
    @FutureOrPresent(message = "La fecha y hora de inicio debe ser en el presente o futuro")
    @Column(nullable = false)
    private Date fechaHoraInicio;

    @NotNull(message = "La fecha y hora de finalización no puede ser nula")
    @Future(message = "La fecha y hora de finalización debe ser en el futuro")
    @Column(nullable = false)
    private Date fechaHoraFinalizacion;

    @NotNull(message = "El número de invitados no puede ser nulo")
    @Min(value = 1, message = "El número de invitados debe ser al menos 1")
    @Column(nullable = false)
    private Integer numeroInvitados;

    @NotNull(message = "El presupuesto final no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El presupuesto final debe ser mayor que 0")
    @Column(nullable = false)
    private Double presupuestoFinal;

    @NotNull(message = "El nombre del evento no puede ser nulo")
    @Size(min = 1, max = 100, message = "El nombre del evento debe tener entre 1 y 100 caracteres")
    @Column(nullable = false)
    private String nombreEvento;

    @NotNull(message = "La ubicación no puede ser nula")
    @Size(min = 1, max = 200, message = "La ubicación debe tener entre 1 y 200 caracteres")
    @Column(nullable = false)
    private String ubicacion;

    @NotNull(message = "La imagen no puede ser nula")
    @Size(min = 1, max = 500, message = "La imagen debe tener entre 1 y 500 caracteres")
    @Column(nullable = false)
    private String imagen;

    @NotNull(message = "El campo editable no puede ser nulo")
    @Column(nullable = false)
    private Boolean editable;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Orden> ordenes = new ArrayList<>();

    // Constructor
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
