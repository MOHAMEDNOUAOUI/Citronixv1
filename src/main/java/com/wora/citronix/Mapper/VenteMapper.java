package com.wora.citronix.Mapper;

import com.wora.citronix.DTO.Vente.CreateVenteDTO;
import com.wora.citronix.DTO.Vente.ResponseVenteDTO;
import com.wora.citronix.Entity.Vente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenteMapper {
    Vente toEntity(CreateVenteDTO createVenteDTO);
    ResponseVenteDTO toResponse(Vente vente);
}
