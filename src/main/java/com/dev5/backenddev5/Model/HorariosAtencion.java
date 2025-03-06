package com.dev5.backenddev5.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "horariosAtencion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorariosAtencion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer servicio_id;

    @Column(nullable = false)
    private Date inicio;

    @Column(nullable = false)
    private Date fin;

    @Column(nullable = false)
    private Time HoraInicioDia;

    @Column(nullable = false)
    private Time HoraFinDia;

    @ManyToOne
    @JoinColumn(name = "servicio_id", insertable = false, updatable = false)
    private Servicio servicio;

    public HorariosAtencion() {
    }

    public HorariosAtencion(Integer id, Integer servicio_id, Date inicio, Date fin, Time horaInicioDia, Time horaFinDia, Servicio servicio) {
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

    public Time getHoraInicioDia() {
        return HoraInicioDia;
    }

    public void setHoraInicioDia(Time horaInicioDia) {
        HoraInicioDia = horaInicioDia;
    }

    public Time getHoraFinDia() {
        return HoraFinDia;
    }

    public void setHoraFinDia(Time horaFinDia) {
        HoraFinDia = horaFinDia;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
}
