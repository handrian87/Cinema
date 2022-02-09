package com.projet.cinema;

import com.projet.cinema.entities.Film;
import com.projet.cinema.service.ICinemaInitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {
    private ICinemaInitService cinemaInitService;
    private RepositoryRestConfiguration restConfiguration;
    public CinemaApplication(ICinemaInitService cinemaInitService, RepositoryRestConfiguration restConfiguration){
        this.cinemaInitService = cinemaInitService;
        this.restConfiguration = restConfiguration;
    }
    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // On va afficher le id de la class Film dans les requêtes affichant les films en json.
        // Donc à chaque fois que Spring va sérialiser un film, il va intégrer le id.
        restConfiguration.exposeIdsFor(Film.class);
        cinemaInitService.initVilles();
        cinemaInitService.initCinemas();
        cinemaInitService.initSalles();
        cinemaInitService.initPlaces();
        cinemaInitService.initSeances();
        cinemaInitService.initCategories();
        cinemaInitService.initFilms();
        cinemaInitService.initProjectionFilm();
        cinemaInitService.initTicket();
    }
}
