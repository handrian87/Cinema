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
@Data @NoArgsConstructor @AllArgsConstructor
public class Film implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private double duree;
    private String realisateur;
    private String description;
    private String photo;
    @Temporal(TemporalType.DATE)
    private Date dateSortie;
    // pour éviter une boucle infinie lors du chargement du film (puisque
    // ce dernier et la Catégorie sont interdépendants. On a une sérialisation
    // bidirectionnelle et quand spring va sérialiser on format json, on va
    // tmober dans une boucle infinie. Car lorsqe spring va convertir une catégorie
    // en format json il va mettre les films dans la catégorie, et dans la catégorie
    // il va mettre les films. Avec Spring, on peut résoudre ce problème, on lui demandant
    // de nous afficher que les informations qui n'ont aucun lien bidirectionnel avec lui.
    // Donc, au lieu de considérer la catégorie, spring va nous donner un lien qu'on peut utiliser
    // si on a besoin des informations sur la catégorie.
    // Par exemple, on va demander à Spring que quand il va donner un film (uo convertir
    // on format json) ce n'est pas la peine de donner les projections dans le film.
    // Pour ce faire, on va utiliser @JsonProperty. Donc cette propriété va nous dire que
    // lorsque on va traduire une propriété en format json, en lecture, ce n'est pas la peine de
    // prendre en considération la projection.On lui dis de ne prendre cet attribut en écriture.
    @OneToMany(mappedBy = "film")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<ProjectionFilm> projectionFilm;
    @ManyToOne
    private Categorie categorie;
}
