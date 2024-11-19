package com.wora.citronix.Mapper;

import com.wora.citronix.DTO.Champ.CreateChampDTO;
import com.wora.citronix.DTO.Champ.ResponseChampDTO;
import com.wora.citronix.Entity.Champ;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChampMapper {
    ResponseChampDTO toResponse(Champ champ);
    Champ toEntity(CreateChampDTO createChampDTO);
}
