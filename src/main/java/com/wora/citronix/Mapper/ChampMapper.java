package com.wora.citronix.Mapper;

import com.wora.citronix.DTO.Champ.CreateChampDTO;
import com.wora.citronix.DTO.Champ.ResponseChampDTO;
import com.wora.citronix.Entity.Champ;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChampMapper {
    @Mapping(source = "ferme" , target = "ferme")
    ResponseChampDTO toResponse(Champ champ);
    Champ toEntity(CreateChampDTO createChampDTO);
}
