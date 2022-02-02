package com.projet.cinema.dao;

import com.projet.cinema.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//L'annotation @RepositoryRestResource signifie que toutes les méthodes qu'on hérite
// de JpaRepository sont accessibles directement via l'API Rest. Spring nous offre
// alors une API web generique.
@RepositoryRestResource
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
