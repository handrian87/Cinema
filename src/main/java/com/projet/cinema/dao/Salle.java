package com.projet.cinema.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Salle implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int nombrePlace;
    @ManyToOne
    private Cinema cinema;
    // Si on ne spécifie pas JoinColumn après @MayToOne,
    // par défaut, Spring va spécfifier une clé étrangère qu'il va appeler cinema
    @OneToMany(mappedBy = "salle")
    private Collection<Place> places;
    @OneToMany(mappedBy = "salle")
    private Collection<ProjectionFilm> projectionFilms;
}
