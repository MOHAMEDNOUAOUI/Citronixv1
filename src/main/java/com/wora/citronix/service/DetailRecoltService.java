package com.wora.citronix.service;

import com.wora.citronix.DTO.DetailRecolt.CreateDetailRecoltDTO;
import com.wora.citronix.DTO.DetailRecolt.ResponseDetailRecoltDTO;

import java.util.List;

public interface DetailRecoltService {
    ResponseDetailRecoltDTO Harvest(CreateDetailRecoltDTO createDetailRecoltDTO);
    ResponseDetailRecoltDTO findHarvest(Long id);
    List<ResponseDetailRecoltDTO> findHarvests();
    ResponseDetailRecoltDTO updateHarvest(CreateDetailRecoltDTO createDetailRecoltDTO);
    boolean deleteHarvest(Long id);
}
