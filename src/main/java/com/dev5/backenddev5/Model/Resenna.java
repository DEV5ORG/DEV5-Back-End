package com.dev5.backenddev5.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resennas") // Evitamos la "ñ" en nombres de tabla
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resenna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer usuarioId;

    @Column(nullable = false)
    private Integer proveedorId;

    @Column(nullable = false)
    private Integer eventoId;

    @Column(nullable = false)
    private Integer calificacion;

    @Column(nullable = false)
    private String comentario;

}
