package com.projet.cinema.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Ticket implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomClient;
    private double prix;
    private int codePaiement;
    private boolean reservee;
    @ManyToOne
    private Place place;
    @ManyToOne
    private ProjectionFilm projectionFilm;
}
