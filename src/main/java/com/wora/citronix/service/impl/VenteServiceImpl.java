package com.wora.citronix.service.impl;

import com.wora.citronix.DTO.Vente.CreateVenteDTO;
import com.wora.citronix.DTO.Vente.ResponseVenteDTO;
import com.wora.citronix.DTO.Vente.embdClient.ClientDTO;
import com.wora.citronix.Entity.Recolte;
import com.wora.citronix.Entity.Vente;
import com.wora.citronix.Entity.emdb.Client;
import com.wora.citronix.Mapper.VenteMapper;
import com.wora.citronix.repository.RecolteRepository;
import com.wora.citronix.repository.VenteRepository;
import com.wora.citronix.service.VenteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VenteServiceImpl implements VenteService {

    private final VenteRepository venteRepository;
    private final RecolteRepository recolteRepository;
    private final VenteMapper venteMapper;

    @Override
    public ResponseVenteDTO createVente(CreateVenteDTO createVenteDTO) {
        Long recolt_id = createVenteDTO.getRecolte_id();
        Vente vente = venteMapper.toEntity(createVenteDTO);
        Recolte recolte = recolteRepository.findById(recolt_id).orElseThrow(() -> new EntityNotFoundException("Recolt not found"));

        if (vente.getDateVente().isBefore(recolte.getDateRecolte())){
            throw new RuntimeException("You cant sell before recolting");
        }

        Double totalVenteRecoltQuantity = recolte.getVenteList() != null ? recolte.getVenteList().stream()
                .mapToDouble(Vente::getQuantity)
                .sum()
                : 0.0;

        double remainingQuantity = recolte.getQuantiteTotal() - totalVenteRecoltQuantity;
        if (createVenteDTO.getQuantity() > remainingQuantity) {
            throw new RuntimeException("you exceeded the limit of the stock , the remaining is :" + remainingQuantity);
        }

        Client client = new Client();
        client.setFirstName(createVenteDTO.getClient().getFirstName());
        client.setLastName(createVenteDTO.getClient().getLastName());
        vente.setClient(client);
        vente.setRecolte(recolte);
        Vente savedVente = venteRepository.save(vente);
        ResponseVenteDTO response= venteMapper.toResponse(savedVente);
        return  response;
    }

    @Override
    public List<ResponseVenteDTO> getALlVentes() {
        List<Vente> ventes = venteRepository.findAll();
        if (ventes.isEmpty()){
            throw new RuntimeException("No ventes found");
        }
         return ventes.stream().map(venteMapper::toResponse).toList();
    }

    @Override
    public ResponseVenteDTO getVenteById(Long id) {
        Vente vente = venteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vente not found"));
        return venteMapper.toResponse(vente);
    }


    @Override
    public boolean deleteVente(Long id) {
        if (venteRepository.existsById(id)){
            venteRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Vente not found");
        }
        return true;
    }

    @Override
    public ResponseVenteDTO updateVente() {
        return null;
    }


    @Override
    public ResponseVenteDTO getVenteByClient(ClientDTO client) {
        return null;
    }
}
