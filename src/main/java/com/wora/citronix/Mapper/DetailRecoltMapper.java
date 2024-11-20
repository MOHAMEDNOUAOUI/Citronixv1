package com.wora.citronix.Mapper;

import com.wora.citronix.DTO.DetailRecolt.CreateDetailRecoltDTO;
import com.wora.citronix.DTO.DetailRecolt.ResponseDetailRecoltDTO;
import com.wora.citronix.Entity.DetailRecolte;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DetailRecoltMapper {
    DetailRecolte toEntity(CreateDetailRecoltDTO createDetailRecoltDTO);
    ResponseDetailRecoltDTO toResponse(DetailRecolte detailRecolte);
}
