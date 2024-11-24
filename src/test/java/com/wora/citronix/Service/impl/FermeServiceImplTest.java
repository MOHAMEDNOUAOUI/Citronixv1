package com.wora.citronix.Service.impl;

import com.wora.citronix.DTO.Ferme.CreateFermeDTO;
import com.wora.citronix.DTO.Ferme.ResponseFermeDTO;
import com.wora.citronix.Entity.Ferme;
import com.wora.citronix.Mapper.FermeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.wora.citronix.service.impl.FermeServiceImpl;
import  com.wora.citronix.repository.FermeRepository;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FermeServiceImplTest {

    @InjectMocks
    private FermeServiceImpl fermeService;

    @Mock
    private FermeRepository fermeRepository;

    @Mock
    private FermeMapper fermeMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createFerme_success() {
        CreateFermeDTO createFermeDTO = new CreateFermeDTO();
        createFermeDTO.setSuperficie(2000D);
        createFermeDTO.setLocalisation("Localisation A");
        createFermeDTO.setDateDeCreation(LocalDate.now());

        Ferme ferme = new Ferme();
        ferme.setId(1L);
        ferme.setSuperficie(2000D);
        ferme.setLocalisation("Localisation A");
        ferme.setDateDeCreation(LocalDate.now());

        ResponseFermeDTO responseFermeDTO = new ResponseFermeDTO();
        responseFermeDTO.setId(1L);
        responseFermeDTO.setSuperficie(2000D);
        responseFermeDTO.setLocalisation("Localisation A");
        responseFermeDTO.setDateDeCreation(LocalDate.now());

        when(fermeMapper.toEntity(createFermeDTO)).thenReturn(ferme);
        when(fermeRepository.save(ferme)).thenReturn(ferme);
        when(fermeMapper.toResponse(ferme)).thenReturn(responseFermeDTO);

        ResponseFermeDTO result = fermeService.createFerme(createFermeDTO);

        assertEquals(2000D, result.getSuperficie());
        assertEquals("Localisation A", result.getLocalisation());
        assertEquals(LocalDate.now(), result.getDateDeCreation());
    }

}