package com.pme.reservations.controlleurs;
import com.pme.reservations.dto.Reservationdto;
import com.pme.reservations.dto.SalleDto;
import com.pme.reservations.entities.Salle;
import com.pme.reservations.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
public class SalleController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("")
    public ResponseEntity<SalleDto> reserverSalle(@RequestBody @Valid Reservationdto input) {
        Optional<SalleDto>  salle = reservationService.reserverSalle(input.getCreneauHoraire(), input.getTypeReunion(), input.getNombrePersonnes());
        return salle.isPresent()? ResponseEntity.ok(salle.get()):ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}