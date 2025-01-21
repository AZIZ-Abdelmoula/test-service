package com.pme.reservations.dto;

import lombok.Getter;

@Getter
public enum TypeReunionDto {
    VC("VisioConférence"),
    SPEC("Séance de partage/étude de cas"),
    RS(" Réunion simple"),
    RC (" Réunion couplée")
    ;
    private final String displayName;

    TypeReunionDto(String displayName) {
        this.displayName = displayName;
    }
}
