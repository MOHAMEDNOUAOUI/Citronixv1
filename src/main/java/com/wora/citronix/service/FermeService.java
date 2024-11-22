package com.wora.citronix.service;

import com.wora.citronix.DTO.Ferme.CreateFermeDTO;
import com.wora.citronix.DTO.Ferme.ResponseFermeDTO;
import com.wora.citronix.DTO.Ferme.SearchFermeDTO;
import com.wora.citronix.DTO.Ferme.UpdateFermeDTO;

import java.util.List;

public interface FermeService {
    ResponseFermeDTO createFerme(CreateFermeDTO createFermeDTO);
    List<ResponseFermeDTO> getAllFermes();
    List<ResponseFermeDTO> getAllFermesByNameAndLocalisation(SearchFermeDTO searchFermeDTO);
    ResponseFermeDTO getFermeById(Long id);
    boolean removeFerme(Long id);
    ResponseFermeDTO updateFerme(UpdateFermeDTO updateFermeDTO , Long id);
}
