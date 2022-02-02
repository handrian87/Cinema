package com.projet.cinema.dao;

import com.projet.cinema.entities.Seance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeanceRepository extends JpaRepository<Seance, Long> {
}
