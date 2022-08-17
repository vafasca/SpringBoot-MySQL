package com.sofka.bingo.repository;

import com.sofka.bingo.domain.CartonBingo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartonBingoRepository extends JpaRepository<CartonBingo, Long> {
    @Query(value = "SELECT crt " +
            "FROM CartonBingo crt " +
            "ORDER BY crt.cartBalota ASC ")
    public List<CartonBingo> findCartonBingoByCartBalota();
}