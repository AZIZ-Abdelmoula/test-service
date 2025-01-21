package com.pme.reservations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pme.reservations.controlleurs.SalleController;
import com.pme.reservations.dto.Reservationdto;
import com.pme.reservations.dto.SalleDto;
import com.pme.reservations.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import  com.pme.reservations.entities.Salle;

import java.util.Optional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;
@SpringBootTest
public class SalleControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private SalleController salleController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // Initialisation de MockMvc avec le contrôleur à tester
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(salleController).build();
    }

   // @Test
    public void testReserverSalleSuccess() throws Exception {
        // je cree un objet Reservationdto
        Reservationdto request = new Reservationdto();
        request.setCreneauHoraire("9h-10h");
        request.setTypeReunion("VC");
        request.setNombrePersonnes(8);

        // je cree un objet Salle de retour
        SalleDto salle = new SalleDto("E1001",20);


        when(reservationService.reserverSalle(anyString(), anyString(), anyInt())).thenReturn(Optional.of(salle));

        // j effectue la requête POST et je verifie la réponse
        mockMvc.perform(post("/api/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("E1001"))
                .andExpect(jsonPath("$.capacite").value(20));

    }

   // @Test
    public void testReserverSalleNotFound() throws Exception {
        // Je cree un objet Reservationdto
        Reservationdto request = new Reservationdto();
        request.setCreneauHoraire("9h00-10h00");
        request.setTypeReunion("VC");
        request.setNombrePersonnes(8);

        // JE simule le service pour qu il retourne null
        when(reservationService.reserverSalle(anyString(), anyString(), anyInt())).thenReturn(null);

        // j effectue la requête POST et je verifie  la réponse 404
        mockMvc.perform(post("/api/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());



}
}
