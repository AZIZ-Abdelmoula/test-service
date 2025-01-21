package com.pme.reservations.repositories;

import com.pme.reservations.entities.Equipement;
import com.pme.reservations.entities.TypeReunion;
import com.pme.reservations.entities.Salle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EquipementRepository extends CrudRepository<Equipement,Long> {

    List<Equipement> findEquipementsByTypeReunion(TypeReunion reunion);
    List<Equipement> findEquipementsBySalle(Salle s);

    @Query("select e from Equipement e where " +
            "e.nombreDisponible > (select count(r) FROM ReservationEquipement r where r.startTime = ?1 and r.endTime = ?2 and r.equipement = e)")
    List<Equipement> findEquipementsDisponible(int startTime, int endTime);
}
