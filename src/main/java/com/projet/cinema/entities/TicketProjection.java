package com.projet.cinema.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.*;

@Projection(name="pt",types={com.projet.cinema.entities.Ticket.class})
public interface TicketProjection {
    public Long getId();
    public String getNomClient();
    public double getPrix();
    public int getCodePaiement();
    public boolean getReservee();
    public Place getPlace();
}
