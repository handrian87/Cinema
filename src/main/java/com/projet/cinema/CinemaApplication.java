package com.projet.cinema;

import com.projet.cinema.service.ICinemaInitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {
    private ICinemaInitService cinemaInitService;
    public CinemaApplication(ICinemaInitService cinemaInitService){
        this.cinemaInitService = cinemaInitService;
    }
    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
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
