package com.wora.citronix.Mapper;

import com.wora.citronix.DTO.Ferme.CreateFermeDTO;
import com.wora.citronix.DTO.Ferme.ResponseFermeDTO;
import com.wora.citronix.Entity.Ferme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FermeMapper {
    Ferme toEntity(CreateFermeDTO createFermeDTO);
    @Mapping(source = "champsList" , target = "champsList")
    ResponseFermeDTO toResponse(Ferme ferme);
}
