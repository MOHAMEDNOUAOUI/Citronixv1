package com.wora.citronix.service;

import com.wora.citronix.DTO.Ferme.CreateFermeDTO;
import com.wora.citronix.DTO.Ferme.ResponseFermeDTO;

import java.util.List;

public interface FermeService {
    ResponseFermeDTO createFerme(CreateFermeDTO createFermeDTO);
    List<ResponseFermeDTO> getAllFermes();
    List<ResponseFermeDTO> getAllFermesByNameAndLocalisation(String name, String Localisation);
    ResponseFermeDTO getFermeById(Long id);
    boolean removeFerme(Long id);
    ResponseFermeDTO updateFerme(CreateFermeDTO createFermeDTO , Long id);
}
