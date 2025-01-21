package com.pme.reservations.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TypeReunion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String description;
    private Integer nbrCollab; //TODO
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "EQUIPEMENT_TYPE_REUNION" )
    private List<Equipement> equipement;
}
