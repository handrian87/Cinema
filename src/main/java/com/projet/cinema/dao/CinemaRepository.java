package com.projet.cinema.dao;

import com.projet.cinema.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {

}
