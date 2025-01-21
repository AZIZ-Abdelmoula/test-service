package com.pme.reservations.Mappers;

import com.pme.reservations.models.Creneau;

public class CreneauMapper {


    public static Creneau mapToCreneau(String creneau) {
        String[] times = creneau.split("-");

        if (times.length != 2) {
            throw new IllegalArgumentException("Le créneau horaire n'est pas valide");
        }

        int startTime = convertToHour(times[0]);

        int endTime = convertToHour(times[1]);

        return new Creneau(startTime, endTime);
    }

    private static int convertToHour(String time) {
        String[] parts = time.split("h");
        if (parts.length != 1) { //LE format est 8h-9h On peut ausssi faire raplace('h','')
            throw new IllegalArgumentException("Le format de l'heure est invalide");
        }
        int hour = Integer.parseInt(parts[0]);
        return hour;
    }
}