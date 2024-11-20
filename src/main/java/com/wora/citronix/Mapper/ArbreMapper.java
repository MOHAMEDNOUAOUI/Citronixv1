package com.wora.citronix.Mapper;

import com.wora.citronix.DTO.Arbre.CreateArbreDTO;
import com.wora.citronix.DTO.Arbre.ResponseArbreDTO;
import com.wora.citronix.Entity.Arbre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArbreMapper {
    @Mapping(source = "champ" , target = "champ")
    @Mapping(source = "detailRecoltes" , target = "detailRecoltes")
    ResponseArbreDTO toResponse(Arbre arbre);
    Arbre toEntit(CreateArbreDTO createArbreDTO);
    Arbre toEntityFromResponse(ResponseArbreDTO responseArbreDTO);
}
