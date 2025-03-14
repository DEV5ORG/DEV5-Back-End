package com.dev5.backenddev5.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "horariosAtencion")
@Getter
@Setter
public class HorariosAtencion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El ID del servicio no puede ser nulo")
    @Column(nullable = false)
    private Integer servicio_id;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    @FutureOrPresent(message = "La fecha de inicio debe ser en el presente o futuro")
    @Column(nullable = false)
    private Date inicio;

    @NotNull(message = "La fecha de fin no puede ser nula")
    @Future(message = "La fecha de fin debe ser en el futuro")
    @Column(nullable = false)
    private Date fin;

    @NotNull(message = "La hora de inicio del día no puede ser nula")
    @Column(nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime HoraInicioDia;

    @NotNull(message = "La hora de fin del día no puede ser nula")
    @Column(nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime HoraFinDia;

    @ManyToOne
    @JoinColumn(name = "servicio_id", insertable = false, updatable = false)
    @JsonBackReference
    private Servicio servicio;

    public HorariosAtencion() {
    }

    public HorariosAtencion(Integer id, Integer servicio_id, Date inicio, Date fin, LocalTime horaInicioDia, LocalTime horaFinDia, Servicio servicio) {
        this.id = id;
        this.servicio_id = servicio_id;
        this.inicio = inicio;
        this.fin = fin;
        HoraInicioDia = horaInicioDia;
        HoraFinDia = horaFinDia;
        this.servicio = servicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServicio_id() {
        return servicio_id;
    }

    public void setServicio_id(Integer servicio_id) {
        this.servicio_id = servicio_id;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public LocalTime getHoraInicioDia() {
        return HoraInicioDia;
    }

    public void setHoraInicioDia(LocalTime horaInicioDia) {
        HoraInicioDia = horaInicioDia;
    }

    public LocalTime getHoraFinDia() {
        return HoraFinDia;
    }

    public void setHoraFinDia(LocalTime horaFinDia) {
        HoraFinDia = horaFinDia;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
}
