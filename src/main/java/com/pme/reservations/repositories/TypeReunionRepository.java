package com.pme.reservations.repositories;

import com.pme.reservations.entities.TypeReunion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeReunionRepository extends CrudRepository<TypeReunion,Long> {

    TypeReunion findFirstByCode(String code);

}
