package com.pme.reservations.entities;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private String creneauHoraire;
    //On peut se contenter de mettre seulement startTime et déduire le endTime en ajoutant une heure, mais j'ai préféré de creer un autre champs pour rester evolutif
    private int  startTime;
    private int endTime;
    private int nombrePersonnes;

    @ManyToOne
    private TypeReunion typeReunion;

    @ManyToOne
    private Salle salle;
    //private boolean reserve;
}

