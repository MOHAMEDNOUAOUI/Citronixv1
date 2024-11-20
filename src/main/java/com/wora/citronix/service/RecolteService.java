package com.wora.citronix.service;

import com.wora.citronix.DTO.Recolt.CreateRecoltDTO;
import com.wora.citronix.DTO.Recolt.ResponseRecoltDTO;

import java.util.List;

public interface RecolteService {
    ResponseRecoltDTO createRecolt(CreateRecoltDTO createRecoltDTO);
    ResponseRecoltDTO findRecoltById(Long id);
    List<ResponseRecoltDTO> getAllRecolts();
    boolean deleteRecolt(Long id);
    ResponseRecoltDTO updateRecolt(CreateRecoltDTO createRecoltDTO , Long id);
}
