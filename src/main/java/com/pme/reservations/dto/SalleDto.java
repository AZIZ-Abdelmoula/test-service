package com.pme.reservations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class SalleDto {
    private String nom;
    private int capaciteMax;
}
