package com.pme.reservations.repositories;

import com.pme.reservations.entities.Reservation;
import com.pme.reservations.entities.Salle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReservationRepository extends CrudRepository<Reservation,Long> {
    List<Reservation> findReservationsBySalleAndStartTimeAndEndTime(Salle salle, int start, int end);
    long countBySalleNomAndAndStartTimeAndEndTime(String salleNom,int start,int end);
}
