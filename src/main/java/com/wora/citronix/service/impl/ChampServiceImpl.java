package com.wora.citronix.service.impl;

import com.wora.citronix.DTO.Champ.CreateChampDTO;
import com.wora.citronix.DTO.Champ.ResponseChampDTO;
import com.wora.citronix.Entity.Champ;
import com.wora.citronix.Entity.Ferme;
import com.wora.citronix.Mapper.ChampMapper;
import com.wora.citronix.repository.ChampsRepository;
import com.wora.citronix.repository.FermeRepository;
import com.wora.citronix.service.ChampService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampServiceImpl implements ChampService {

    @Autowired
    private ChampsRepository champsRepository;

    @Autowired
    private FermeRepository fermeRepository;

    @Autowired
    private ChampMapper champMapper;


    @Override
    public ResponseChampDTO createChamp(CreateChampDTO createChampDTO) {
        Champ champ = champMapper.toEntity(createChampDTO);
        Ferme ferme = fermeRepository.findById(createChampDTO.getFerme_id()).orElseThrow(() -> new EntityNotFoundException("Ferme not found"));
        if (ferme.getSuperficie()/2 > champ.getSuperficie()){
            Champ savedChamp = champsRepository.save(champ);
            return champMapper.toResponse(savedChamp);
        }else{
            throw new RuntimeException("champ ne peut dépasser "+ferme.getSuperficie()/2+" de la superficie totale de la ferme.");
        }
    }

    @Override
    public List<ResponseChampDTO> getAllChamps() {
        List<Champ> champs = champsRepository.findAll();
        if (champs.isEmpty()){
            throw new EntityNotFoundException("Nothing found in champs");
        }
        return champs.stream().map(champMapper::toResponse).toList();
    }

    @Override
    public ResponseChampDTO getChampsById(Long id) {
        Champ champ = champsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Champ does not exist"));
        return champMapper.toResponse(champ);
    }

    @Override
    public boolean deleteChamps(Long id) {
        Champ champ = champsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Champ does not exist"));
        champsRepository.delete(champ);
        return true;
    }

    @Override
    public ResponseChampDTO updateChamp(CreateChampDTO createChampDTO) {
        return null;
    }
}
