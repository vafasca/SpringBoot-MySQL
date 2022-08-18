package com.sofka.bingo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
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
    private Integer id;

    @Column(name = "play_mongo_id", nullable = false, length = 45)
    private String playMongoId;

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = Room.class,
            optional = false
    )
    @JsonBackReference
    @JoinColumn(name = "room_room_id", nullable = false)
    private Room roomRoom;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "playerIdplayer",
            targetEntity = CartonBingo.class,
            cascade = CascadeType.REMOVE
    )
    @JsonManagedReference
    private List<CartonBingo> cartonBingos = new ArrayList<>();

}