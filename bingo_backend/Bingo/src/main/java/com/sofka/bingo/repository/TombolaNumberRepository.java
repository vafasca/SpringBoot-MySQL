package com.sofka.bingo.repository;

import com.sofka.bingo.domain.TombolaNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TombolaNumberRepository extends JpaRepository<TombolaNumber, Long> {
}