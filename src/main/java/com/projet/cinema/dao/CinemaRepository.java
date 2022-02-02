package com.projet.cinema.dao;

import com.projet.cinema.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

}
