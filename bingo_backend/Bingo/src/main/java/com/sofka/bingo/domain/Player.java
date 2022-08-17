package com.sofka.bingo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "player")
public class Player implements Serializable {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idplayer", nullable = false)
    private Long id;

    @Column(name = "play_mongo_id", length = 45)
    private String playMongoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_room_id")
    private Room roomRoom;

}