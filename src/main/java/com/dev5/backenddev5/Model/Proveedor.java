package com.dev5.backenddev5.Model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "proveedores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String tipoServicio;

    @Column(nullable = false)
    private String contacto;

    @Column(nullable = false)
    private Integer calificacion;



}
