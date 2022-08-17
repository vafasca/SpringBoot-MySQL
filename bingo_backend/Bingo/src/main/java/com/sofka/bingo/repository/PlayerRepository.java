package com.sofka.bingo.repository;

import com.sofka.bingo.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}