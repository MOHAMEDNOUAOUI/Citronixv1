package com.wora.citronix.service;

import com.wora.citronix.DTO.Champ.CreateChampDTO;
import com.wora.citronix.DTO.Champ.ResponseChampDTO;

import java.util.List;

public interface ChampService {
    ResponseChampDTO createChamp(CreateChampDTO createChampDTO);
    List<ResponseChampDTO> getAllChamps();
    ResponseChampDTO getChampsById(Long id);
    boolean deleteChamps(Long id);
    ResponseChampDTO updateChamp(CreateChampDTO createChampDTO);
}

