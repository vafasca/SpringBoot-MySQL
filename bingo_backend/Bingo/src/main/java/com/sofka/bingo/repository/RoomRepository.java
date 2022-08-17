package com.sofka.bingo.repository;

import com.sofka.bingo.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(value = "SELECT rm " +
            "FROM Room rm " +
            "ORDER BY rm.id ASC ")
    public List<Room> findLastRoom();
}