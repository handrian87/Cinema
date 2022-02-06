package com.projet.cinema.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class ProjectionFilm implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateProjection;
    private double prix;
    @ManyToOne
    private Film film;
    @ManyToOne
    private Salle salle;
    @OneToMany(mappedBy = "projectionFilm")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Ticket> ticket;
    @ManyToOne
    private Seance seance;
}
