package com.pme.reservations.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Creneau {
    private int startTime;
    private int endTime;
}
