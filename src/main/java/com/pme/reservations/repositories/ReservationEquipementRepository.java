package com.pme.reservations.repositories;

import com.pme.reservations.entities.Reservation;
import com.pme.reservations.entities.ReservationEquipement;
import com.pme.reservations.entities.Salle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationEquipementRepository extends CrudRepository<ReservationEquipement,Long> {
}
