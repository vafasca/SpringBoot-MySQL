package com.sofka.bingo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tombola_number")
public class TombolaNumber implements Serializable {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tom_id", nullable = false)
    private Long id;

    @Column(name = "tom_balota")
    private Integer tomBalota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_room_id")
    private Room roomRoom;

}