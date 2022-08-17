package com.sofka.bingo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "room")
public class Room implements Serializable {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    private Integer id;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "roomRoom",
            targetEntity = Player.class,
            cascade = CascadeType.REMOVE
    )
    @JsonManagedReference
    private List<Player> players = new ArrayList<>();

    @OneToMany(mappedBy = "roomRoom")
    private List<TombolaNumber> tombolaNumbers = new ArrayList<>();

}