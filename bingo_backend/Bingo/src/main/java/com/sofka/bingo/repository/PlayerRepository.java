package com.sofka.bingo.repository;

import com.sofka.bingo.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Query(value = "SELECT rm " +
            "FROM Player rm " +
            "ORDER BY rm.id ASC ")
    public List<Player> getPlayersById();

    @Query(value = "SELECT rm.id " +
            "FROM Player rm " +
            "WHERE rm.playMongoId = :idMongo"
    )
    public Integer getIndexPlayer(@Param(value = "idMongo") String idMongo);//muestra el id del jugador buscado


}