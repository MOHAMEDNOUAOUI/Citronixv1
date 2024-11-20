package com.wora.citronix.service;

import com.wora.citronix.DTO.Recolt.CreateRecoltDTO;
import com.wora.citronix.DTO.Recolt.ResponseRecoltDTO;
import com.wora.citronix.DTO.Recolt.UpdateRecoltDTO;

import java.util.List;

public interface RecolteService {
    ResponseRecoltDTO createRecolt(CreateRecoltDTO createRecoltDTO);
    ResponseRecoltDTO findRecoltById(Long id);
    List<ResponseRecoltDTO> getAllRecolts();
    boolean deleteRecolt(Long id);
    ResponseRecoltDTO updateRecolt(UpdateRecoltDTO updateRecoltDTO , Long id);
}
