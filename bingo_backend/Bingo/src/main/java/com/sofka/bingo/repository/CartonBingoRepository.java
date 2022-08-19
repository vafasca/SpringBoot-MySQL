package com.sofka.bingo.repository;

import com.sofka.bingo.domain.CartonBingo;
import com.sofka.bingo.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartonBingoRepository extends JpaRepository<CartonBingo, Integer> {
    @Query(value = "SELECT crt " +
            "FROM CartonBingo crt " +
            "ORDER BY crt.cartBalota ASC ")
    public List<CartonBingo> findCartonBingoByCartBalota();

    @Query(value = "SELECT crt " +
            "FROM CartonBingo crt " +
            "WHERE (crt.playerIdplayer = :filter)" +
            "ORDER BY crt.cartBalota ASC ")
    public List<CartonBingo> findJustPlayer(@Param(value = "filter") Integer filter);
}