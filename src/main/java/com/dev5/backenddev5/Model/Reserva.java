package com.dev5.backenddev5.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reservas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer eventoId;

    @Column(nullable = false)
    private Integer usuarioId;

    @Column(nullable = false)
    private String fechaReserva;

    @Column(nullable = false)
    private String estadoReserva;
}
