package com.pme.reservations.services;

import com.pme.reservations.Mappers.CreneauMapper;
import com.pme.reservations.dto.SalleDto;
import com.pme.reservations.entities.*;
import com.pme.reservations.models.Creneau;
import com.pme.reservations.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationEquipementRepository reservationEquipementRepository;

    @Autowired
    private TypeReunionRepository typeReunionRepository;

    @Autowired
    private EquipementRepository equipementRepository;

    @Value("${param.cleaning.time}")
    int cleaningTime;
    @Value("${param.capacite.salle}")
    float capaciteMax;

    @Transactional
    public Optional<SalleDto> reserverSalle(String valCreneau, String valTypeReunion, int nombrePersonnes) {
        // Etape 1 : Récupérer toutes les salles disponibles

        TypeReunion typeReunion=typeReunionRepository.findFirstByCode(valTypeReunion);

        Creneau creneau= CreneauMapper.mapToCreneau(valCreneau);

        Iterable<Salle> sallesDisponibles = salleRepository.findAll();
        //  List<Salle>  sallesDisponibles = salleRepository.findSallesByCapaciteMaxIsGreaterThan(nombrePersonnes/capaciteMax);
        Optional<Salle> salleAdaptee = Optional.empty();



        for (Salle salle : sallesDisponibles) {
            //Etape 1 : Je Vérifie si la salle a la capacité suffisante sinn je passe à la salle suivante
            //On peut creer une requete pour récuperer que les slles ayants la capacité suffisante
            if (salle.getCapaciteMax() * capaciteMax < nombrePersonnes) {
                continue;
            }

            // Etape 2  : Je Vérifie sila salle est libre pour le créneau choisi
            if (!VerifierDisponibilite(salle, creneau)) {
                continue;
            }

            // Vérifier le temps tampon pour le nettoyage
            if (!verifierDesinfection(salle, creneau)) {
                continue;
            }

            // Etape 3 : Je Vérifie si la salle a les équipements nécessaires
            if (!VerfierEquipements(salle, typeReunion, creneau)) {

                continue;
            }

            
            salleAdaptee= Optional.of(salleOptimale(salleAdaptee,salle,typeReunion,nombrePersonnes));

        }
        if(salleAdaptee.isPresent()){
            reserverSalleAvcEquipement(salleAdaptee.get(),creneau,typeReunion,nombrePersonnes);
            return Optional.of(new SalleDto(salleAdaptee.get().getNom(),salleAdaptee.get().getCapaciteMax()));
        } else {
            return Optional.empty();
        }
    }

    private void reserverSalleAvcEquipement(Salle salle, Creneau creneau, TypeReunion typeReunion, int nombrePersonnes) {
        Reservation reservation = new Reservation();
        reservation.setSalle(salle);
        reservation.setStartTime(creneau.getStartTime());
        reservation.setEndTime(creneau.getEndTime());
        reservation.setTypeReunion(typeReunion);
        reservation.setNombrePersonnes(nombrePersonnes);
        reservationRepository.save(reservation);
        //Si la salle ne contient pas l'équipement on le réserve
        typeReunion.getEquipement().forEach(equipement -> {
            if(!salle.getEquipement().contains(equipement)){
                ReservationEquipement reservationEquipement = new ReservationEquipement();
                reservationEquipement.setEquipement(equipement);
                reservationEquipement.setSalle(salle);
                reservationEquipement.setStartTime(creneau.getStartTime());
                reservationEquipement.setEndTime(creneau.getEndTime());
                reservationEquipementRepository.save(reservationEquipement);
            }
        });
    }

    private boolean VerfierEquipements(Salle salle, TypeReunion typeReunion, Creneau creneau) {
        //TODO if(reunion)
        List<Equipement> salleEquipements = equipementRepository.findEquipementsBySalle(salle);
        List<Equipement> reunionEquipements = equipementRepository.findEquipementsByTypeReunion(typeReunion);
        reunionEquipements.removeAll(equipementRepository.findEquipementsDisponible(creneau.getStartTime(),creneau.getEndTime()));
        return salleEquipements.containsAll(reunionEquipements);
    }

    private boolean VerifierDisponibilite(Salle salle, Creneau creneau) {
        return (reservationRepository.countBySalleNomAndAndStartTimeAndEndTime(salle.getNom(),creneau.getStartTime(),creneau.getEndTime())==0);
    }
  //Je verifie si la salle n'est pas reservée une heure avant et une heure aprés pour permettre la desinfection
    private boolean verifierDesinfection(Salle salle, Creneau creneau) {
        boolean hourBefore=(reservationRepository.countBySalleNomAndAndStartTimeAndEndTime(salle.getNom(),creneau.getStartTime()-cleaningTime,creneau.getEndTime()-cleaningTime)==0);
        boolean hourAfter=(reservationRepository.countBySalleNomAndAndStartTimeAndEndTime(salle.getNom(),creneau.getStartTime()+cleaningTime,creneau.getEndTime()+cleaningTime)==0);
        return (hourBefore && hourAfter );
    }
  //Pour récuperer la salle optimale parmis les salles dispo
    private Salle salleOptimale(Optional<Salle> salleAdaptee, Salle nvlSalle, TypeReunion typeReunion, int nombrePersonnes) {
        //TODO
       if( salleAdaptee.isPresent() ) {
             /*return salleAdaptee.get().getEquipement().size() < nvlSalle.getEquipement().size()?salleAdaptee.get():
                    salleAdaptee.get().getEquipement().size() > nvlSalle.getEquipement().size()?nvlSalle:
                    salleAdaptee.get().getCapaciteMax()<nvlSalle.getCapaciteMax()?salleAdaptee.get():nvlSalle;*/
             return salleAdaptee.get().getCapaciteMax()<nvlSalle.getCapaciteMax()?salleAdaptee.get():nvlSalle;
       }
        return nvlSalle;
    }

}