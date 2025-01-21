package com.pme.reservations.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservationdto {
    @NotNull(message = "Le créneau horaire est requis.")
    private String creneauHoraire;

    @NotNull(message = "Le type de réunion est requis.")
    private String typeReunion;

    @Positive(message = "Le nombre de personnes doit être positif.")
    private int nombrePersonnes;

}
