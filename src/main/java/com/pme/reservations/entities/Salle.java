package com.pme.reservations.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private int capaciteMax;
    //Pour rendre les equipement paramétrable au niveau de la base,j'ai choisi d'opter pour cette solution au lieu d'une liste String
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "EQUIPEMENT_SALLE",  // Nom de la table de jointure
            joinColumns = @JoinColumn(name = "salle_id"),  // Clé étrangère vers la salle
            inverseJoinColumns = @JoinColumn(name = "equipement_id")  // Clé étrangère vers l'équipement
    )

    private List<Equipement> equipement;  // Liste des équipements associés à la salle
    @OneToMany(mappedBy = "salle")
    private List<Reservation> reservation;
}
