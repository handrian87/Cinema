package com.projet.cinema.dao;

import com.projet.cinema.entities.ProjectionFilm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectionRepository extends JpaRepository<ProjectionFilm, Long> {
}
