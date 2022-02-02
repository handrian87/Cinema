package com.projet.cinema.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Ville implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double longitude, latitude, altitude;
    // Dans le code suivant, dans mappedBy = "ville", "ville"
    // se trouve dans la variable/l'attribut définie dans la Class Cinema.
    @OneToMany(mappedBy = "ville")
    private Collection<Cinema> cinemas;
}
