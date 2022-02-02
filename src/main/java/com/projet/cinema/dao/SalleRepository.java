package com.projet.cinema.dao;

import com.projet.cinema.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleRepository extends JpaRepository<Salle, Long> {
}
