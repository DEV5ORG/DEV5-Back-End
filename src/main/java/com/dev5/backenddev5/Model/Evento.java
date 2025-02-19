package com.dev5.backenddev5.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "eventos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fecha;

    @Column(nullable = false)
    private Integer numeroInvitados;

    @Column(nullable = false)
    private Double presupuesto;

    @Column(nullable = false)
    private String nombreEvento;

    @Column(nullable = false)
    private Integer usuario_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuario_fk")
    private Usuario usuarioEvento;
}
