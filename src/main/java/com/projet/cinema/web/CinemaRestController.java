package com.projet.cinema.web;

import com.projet.cinema.dao.CategorieRepository;
import com.projet.cinema.dao.FilmRepository;
import com.projet.cinema.dao.TicketRepository;
import com.projet.cinema.entities.Categorie;
import com.projet.cinema.entities.Film;
import com.projet.cinema.entities.Ticket;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaRestController {
    private FilmRepository filmRepository;
    private CategorieRepository categorieRepository;
    private TicketRepository ticketRepository;
    public CinemaRestController(FilmRepository filmRepository, CategorieRepository categorieRepository,
                                TicketRepository ticketRepository){
        this.filmRepository = filmRepository;
        this.categorieRepository = categorieRepository;
        this.ticketRepository = ticketRepository;
    }

//    @GetMapping("/listFilms")
//    public List<Film> films() {
//        return  filmRepository.findAll();
//    }
//
//    @GetMapping("/listCategories")
//    public List<Categorie> categorieList() {
//        return categorieRepository.findAll();
//    }

    // Méthode permettant de consulter une image dans un dossier précis
    @GetMapping(path = "/imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name="id") Long id) throws IOException {
        Film f = filmRepository.findById(id).get();
        String photoName = f.getPhoto();
        // Le code suivant va donner le dossier de l'utilisateur actuel
        File file = new File(System.getProperty("user.home")+"/cinema/"+photoName);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    // Méthode pour la gestion des paiements des tickets.
    // Comme TicketForm viens dans le corps de la requête (en argument de la méthode),
    // on va utiliser l'annotation @RequestBody, pour dire que les données du Ticket
    // sont envoyées dans le corps de la requête en format json.
    @PostMapping("/payerTickets")
    public List<Ticket> payerTicket(@RequestBody TicketForm ticketForm) {
        List<Ticket> ticket = new ArrayList<Ticket>();
        ticketForm.getTickets().forEach(idTicket -> {
            Ticket ticket1 = ticketRepository.findById(idTicket).get();
            ticket1.setNomClient(ticketForm.getNomClient());
            ticket1.setReservee(true);
            ticketRepository.save(ticket1);
            ticket.add(ticket1);
        });
        return  ticket;
    }

}
    @Data
    class TicketForm {
        private String nomClient;
        private int codePayement;
        private List<Long> tickets = new ArrayList<Long>();
    }
