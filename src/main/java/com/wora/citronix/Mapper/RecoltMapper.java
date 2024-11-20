package com.wora.citronix.Mapper;

import com.wora.citronix.DTO.Recolt.CreateRecoltDTO;
import com.wora.citronix.DTO.Recolt.ResponseRecoltDTO;
import com.wora.citronix.Entity.Recolte;
import com.wora.citronix.service.RecolteService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecoltMapper {
    Recolte toEntity(CreateRecoltDTO createRecoltDTO);
    ResponseRecoltDTO toResponse(Recolte recolte);
    Recolte toEntityFromResponse(ResponseRecoltDTO responseRecoltDTO);
}
