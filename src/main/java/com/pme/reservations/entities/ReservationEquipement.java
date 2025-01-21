package com.pme.reservations.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ReservationEquipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private String creneauHoraire;
    //On peut se contenter de mettre seulement startTime et déduire le endTime en ajoutant une
    // heure, mais j'ai préféré de creer un autre champs pour rester evolutif et mieux gerer les données
    private int  startTime;
    private int endTime;

    @ManyToOne
    private Equipement equipement;

    @ManyToOne
    private Salle salle;
}

