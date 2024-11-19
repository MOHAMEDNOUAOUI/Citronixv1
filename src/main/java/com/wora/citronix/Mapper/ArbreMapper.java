package com.wora.citronix.Mapper;

import com.wora.citronix.DTO.Arbre.CreateArbreDTO;
import com.wora.citronix.DTO.Arbre.ResponseArbreDTO;
import com.wora.citronix.Entity.Arbre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArbreMapper {
    ResponseArbreDTO toResponse(Arbre arbre);
    Arbre toEntit(CreateArbreDTO createArbreDTO);
}
