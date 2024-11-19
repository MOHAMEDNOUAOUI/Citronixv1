package com.wora.citronix.Mapper;

import com.wora.citronix.DTO.Ferme.CreateFermeDTO;
import com.wora.citronix.DTO.Ferme.ResponseFermeDTO;
import com.wora.citronix.Entity.Ferme;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FermeMapper {
    Ferme toEntity(CreateFermeDTO createFermeDTO);
    ResponseFermeDTO toResponse(Ferme ferme);
}
