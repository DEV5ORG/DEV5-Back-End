package com.dev5.backenddev5.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servicio")
@Getter
@Setter
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @NotNull(message = "El tipo de servicio no puede ser nulo")
    @Size(min = 1, max = 100, message = "El tipo de servicio debe tener entre 1 y 100 caracteres")
    @Column(nullable = false)
    private String tipoServicio;

    @NotNull(message = "La imagen no puede ser nula")
    @Size(min = 1, max = 500, message = "La imagen debe tener entre 1 y 500 caracteres")
    @Column(nullable = false)
    private String imagen;

    @NotNull(message = "La ubicación no puede ser nula")
    @Size(min = 1, max = 200, message = "La ubicación debe tener entre 1 y 200 caracteres")
    @Column(nullable = false)
    private String ubicacion;

    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<HorariosAtencion> horariosDeAtencion = new ArrayList<>();

    public Servicio() {
    }

    public Servicio(Integer id, Usuario usuario, String tipoServicio, String imagen, String ubicacion, List<Item> items, List<HorariosAtencion> horariosDeAtencion) {
        this.id = id;
        this.usuario = usuario;
        this.tipoServicio = tipoServicio;
        this.imagen = imagen;
        this.ubicacion = ubicacion;
        this.items = items;
        this.horariosDeAtencion = horariosDeAtencion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<HorariosAtencion> getHorariosDeAtencion() {
        return horariosDeAtencion;
    }

    public void setHorariosDeAtencion(List<HorariosAtencion> horariosDeAtencion) {
        this.horariosDeAtencion = horariosDeAtencion;
    }
}
