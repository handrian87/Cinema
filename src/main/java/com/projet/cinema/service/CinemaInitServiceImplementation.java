package com.projet.cinema.service;

import com.projet.cinema.dao.*;
import com.projet.cinema.entities.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitServiceImplementation implements ICinemaInitService {
    private VilleRepository villeRepository;
    private CinemaRepository cinemaRepository;
    private SalleRepository salleRepository;
    private PlaceRepository placeRepository;
    private SeanceRepository seanceRepository;
    private FilmRepository filmRepository;
    private CategorieRepository categorieRepository;
    private ProjectionRepository projectionRepository;
    private TicketRepository ticketRepository;


    public CinemaInitServiceImplementation(VilleRepository villeRepository, CinemaRepository cinemaRepository,
                                           SalleRepository salleRepository, PlaceRepository placeRepository,
                                           SeanceRepository seanceRepository, FilmRepository filmRepository,
                                           CategorieRepository categorieRepository, ProjectionRepository projectionRepository,
                                           TicketRepository ticketRepository){
        this.villeRepository = villeRepository;
        this.cinemaRepository = cinemaRepository;
        this.salleRepository = salleRepository;
        this.placeRepository = placeRepository;
        this.seanceRepository = seanceRepository;
        this.filmRepository = filmRepository;
        this.categorieRepository = categorieRepository;
        this.projectionRepository = projectionRepository;
        this.ticketRepository = ticketRepository;
    }
    @Override
    public void initVilles() {
        Stream.of("Montreal", "Tananarive", "Ottawa", "Monaco").forEach(nameVille -> {
            Ville ville = new Ville();
            ville.setName(nameVille);
            villeRepository.save(ville);
        });
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(ville -> {
            Stream.of("Megarama", "Orion", "Ritz", "Guzzo", "Cineplex").forEach(nameCinema -> {
                Cinema cinema = new Cinema();
                cinema.setName(nameCinema);
                cinema.setNombreSalle(3+(int)(Math.random()*7));
                cinema.setVille(ville);
                cinemaRepository.save(cinema);
            });
        });
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema -> {
            for(int i=0; i<cinema.getNombreSalle(); ++i){
                Salle salle = new Salle();
                salle.setName("S -."+i+1);
                salle.setCinema(cinema);
                salle.setNombrePlace(50+(int)(Math.random()*2));
                salleRepository.save(salle);
            }
        });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for(int i=0; i<salle.getNombrePlace(); ++i){
                Place place = new Place();
                place.setNumero(i+1);
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });
    }

    @Override
    public void initSeances() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(seance -> {
            Seance seance_ = new Seance();
            try {
                seance_.setHeureDebut(dateFormat.parse(seance));
                seanceRepository.save(seance_);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initCategories() {
        Stream.of("Horror","Drama", "Action", "Porn", "Toon").forEach(categ -> {
            Categorie categorie = new Categorie();
            categorie.setName(categ);
            categorieRepository.save(categorie);
        });
    }

    @Override
    public void initFilms() {
        double[] duree = new double[]{1, 1.5, 2, 1.25, 2.5, 3};
        List<Categorie> categorieList = categorieRepository.findAll();
        Stream.of("Game of Thrones", "Seigneur des anneaux", "The godfather", "Hitman", "Mission impossible","Kung Fu soccer")
                .forEach(movieTitle -> {
                    Film film = new Film();
                    film.setTitre(movieTitle);
                    film.setDuree(duree[new Random().nextInt(duree.length)]);
                    film.setPhoto(movieTitle.replaceAll(" ",""));
                    film.setCategorie(categorieList.get(new Random().nextInt(categorieList.size())));
                    filmRepository.save(film);
                }
        );
    }

    @Override
    public void initProjectionFilm() {
        double[] prix = new double[]{7.5, 15, 17, 20, 5};
        villeRepository.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle -> {
                    filmRepository.findAll().forEach(film -> {
                        seanceRepository.findAll().forEach(seance -> {
                            ProjectionFilm projectionFilm = new ProjectionFilm();
                            projectionFilm.setDateProjection(new Date());
                            projectionFilm.setFilm(film);
                            projectionFilm.setPrix(prix[new Random().nextInt(prix.length)]);
                            projectionFilm.setSalle(salle);
                            projectionFilm.setSeance(seance);
                            projectionRepository.save(projectionFilm);
                        });
                    });
                });
            });
        });
    }

    @Override
    public void initTicket() {
        projectionRepository.findAll().forEach(projection -> {
            projection.getSalle().getPlaces().forEach(place -> {
                Ticket ticket = new Ticket();
                ticket.setPlace(place);
                ticket.setProjectionFilm(projection);
                ticket.setPrix(projection.getPrix());
                ticket.setReservee(false);

                ticketRepository.save(ticket);
            });
        });
    }
}
