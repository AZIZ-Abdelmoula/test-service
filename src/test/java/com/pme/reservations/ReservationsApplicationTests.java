package com.pme.reservations;

import com.pme.reservations.dto.Reservationdto;
import com.pme.reservations.dto.SalleDto;
import com.pme.reservations.entities.Salle;
import com.pme.reservations.services.ReservationService;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationsApplicationTests {

	@Autowired
	ReservationService reservationService;

	@Test
	void contextLoads() {
	}

	AbstractMap.SimpleEntry<Reservationdto, String>[] reservations = new AbstractMap.SimpleEntry[]{
			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("9h00-10h00", "VC", 8), "E1001"),
			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("9h00-10h00", "VC", 6), "E1002"),
			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("11h00-12h00", "RC", 4), "E1001"),
			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("11h00-12h00", "RS", 2), "E1002"),
			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("11h00-12h00", "SPEC", 9), "E2002"),

			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("9h00-10h00", "RC", 7), "E2002"),
			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("8h00-9h00", "VC", 9), "E2002"),
			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("8h00-9h00", "SPEC", 10), "E2002"),
			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("9h00-10h00", "SPEC", 5), "E2002"),
			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("9h00-10h00", "RS", 4), "E2002"),

			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("9h00-10h00", "RC", 8), "E2002"),
			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("11h00-12h00", "VC", 12), "E2002"),
			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("11h00-12h00", "SPEC", 5), "E2002"),
			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("8h00-9h00", "VC", 3), "E2002"),
			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("8h00-9h00", "SPEC", 2), "E2002"),

			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("8h00-9h00", "VC", 12), "E2002"),
			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("10h00-11h00", "VC", 6), "E2002"),
			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("11h00-12h00", "RS", 2), "E2002"),
			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("9h00-10h00", "RS", 4), "E2002"),
			new AbstractMap.SimpleEntry<Reservationdto, String>(new Reservationdto("9h00-10h00", "RC", 7), "E2002"),
	};


	@RepeatedTest(20)
	void givenDataIsParamtered_whenCheckAgendaTest_thenGettingRightRooms(RepetitionInfo repetitionInfo){
		AbstractMap.SimpleEntry<Reservationdto, String> input = reservations[repetitionInfo.getCurrentRepetition()-1];
		Optional<SalleDto> salle = reservationService.reserverSalle(input.getKey().getCreneauHoraire(), input.getKey().getTypeReunion(), input.getKey().getNombrePersonnes());
		System.out.println( "[ "+input.getKey().getCreneauHoraire()+" , "+input.getKey().getTypeReunion()+" , "+input.getKey().getNombrePersonnes() + " ] -> " + (salle.isPresent()? salle.get().getNom():"NONE"));
		assertNotEquals( "NONE", salle.isPresent()? salle.get().getNom():"NONE" );
	}

}
