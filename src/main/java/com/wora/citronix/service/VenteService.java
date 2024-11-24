package com.wora.citronix.service;

import com.wora.citronix.DTO.Vente.CreateVenteDTO;
import com.wora.citronix.DTO.Vente.ResponseVenteDTO;
import com.wora.citronix.DTO.Vente.embdClient.ClientDTO;

import java.util.List;

public interface VenteService {
    ResponseVenteDTO createVente(CreateVenteDTO createVenteDTO);
    List<ResponseVenteDTO> getALlVentes();
    ResponseVenteDTO getVenteById(Long id);
    ResponseVenteDTO getVenteByClient(ClientDTO client);
    boolean deleteVente(Long id);
    ResponseVenteDTO updateVente(CreateVenteDTO createVenteDTO , Long id);
}
