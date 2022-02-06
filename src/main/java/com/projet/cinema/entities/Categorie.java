package com.projet.cinema.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Categorie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 75)
    private String name;
    // Comme dans film, on va dire à spring que quand on lui demande
    // d'afficher un film , ce n'est pas la peine de considérer la liste
    // des films (écriture), mais ne le considérer que lorsqu'on est dans l'écriture
    // (enregistrement). JsonIgnore: ignorer un attribut aussi bien en lecture qu'en
    // écriture.
    @OneToMany(mappedBy = "categorie")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Film> film;
}
