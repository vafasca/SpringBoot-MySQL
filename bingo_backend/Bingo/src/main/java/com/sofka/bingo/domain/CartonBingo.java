package com.sofka.bingo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "carton_bingo")
public class CartonBingo implements Serializable {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Integer id;

    @Column(name = "cart_balota")
    private Integer cartBalota;

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = Player.class,
            optional = false
    )
    @JsonBackReference
    @JoinColumn(name = "player_idplayer")
    private Player playerIdplayer;



//    public void setPlayerIdplayer(Player playerIdplayer) {
//        this.playerIdplayer = playerIdplayer;
//    }

}