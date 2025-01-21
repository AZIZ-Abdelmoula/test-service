package com.pme.reservations.repositories;

import com.pme.reservations.entities.Salle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalleRepository extends CrudRepository <Salle,Long> {
    List<Salle> findSallesByCapaciteMaxIsGreaterThan(int nbrPersonnes);



}
