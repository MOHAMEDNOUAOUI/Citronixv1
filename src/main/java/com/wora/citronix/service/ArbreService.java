package com.wora.citronix.service;

import com.wora.citronix.DTO.Arbre.CreateArbreDTO;
import com.wora.citronix.DTO.Arbre.ResponseArbreDTO;

import java.util.List;

public interface ArbreService {
    ResponseArbreDTO createArbre(CreateArbreDTO createArbreDTO);
    ResponseArbreDTO getArbreById(Long id);
    List<ResponseArbreDTO> getAllArbres();
    boolean deleteArbre(Long id);
    ResponseArbreDTO updateArebre(CreateArbreDTO createArbreDTO , Long id);
}
