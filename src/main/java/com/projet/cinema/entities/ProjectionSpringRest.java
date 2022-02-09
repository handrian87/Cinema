package com.projet.cinema.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;

@Projection(name="p1", types={com.projet.cinema.entities.ProjectionFilm.class})
public interface ProjectionSpringRest {
    // Quand on demande à Spring de nous donner une ProjectionFilm en utilisant
    // la projectionFilm "p1", voilà les informations qu'il doit nous retourner.
    public Long getId();
    public double getPrix();
    public Date getDateProjection();
    public Salle getSalle();
    public Film getFilm();
    public Seance getSeance();
    public Collection<Ticket> getTicket();
    // pour appeler une projectionFilm de id=2 dont la projection s'appelle "p1"
    // http://localhost:8080/projectionFilms/2?projection=p1
}
