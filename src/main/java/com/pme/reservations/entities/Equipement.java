package com.pme.reservations.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private int nombreDisponible;

    @ManyToMany  // Cette relation est définie côté 'Salle'
    @JoinTable( name = "EQUIPEMENT_SALLE" )
    private List<Salle> salle;
    @ManyToMany
    @JoinTable( name = "EQUIPEMENT_TYPE_REUNION" )
    private List<TypeReunion> typeReunion;
    @OneToMany(mappedBy = "equipement")
    private List<ReservationEquipement> reservation;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Equipement)) {
            return false;
        }
        return this.getNom().equals(((Equipement) o).getNom());
    }
}
