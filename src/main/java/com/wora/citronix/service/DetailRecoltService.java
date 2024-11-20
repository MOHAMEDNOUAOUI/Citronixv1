package com.wora.citronix.service;

import com.wora.citronix.DTO.DetailRecolt.CreateDetailRecoltDTO;
import com.wora.citronix.DTO.DetailRecolt.ResponseDetailRecoltDTO;
import com.wora.citronix.Entity.emdb.DetailRecolteId;

import java.util.List;

public interface DetailRecoltService {
    ResponseDetailRecoltDTO Harvest(CreateDetailRecoltDTO createDetailRecoltDTO);
    ResponseDetailRecoltDTO findHarvest(DetailRecolteId id);
    List<ResponseDetailRecoltDTO> findHarvests();
    ResponseDetailRecoltDTO updateHarvest(CreateDetailRecoltDTO createDetailRecoltDTO , DetailRecolteId ids);
    boolean deleteHarvest(DetailRecolteId ids);
}
