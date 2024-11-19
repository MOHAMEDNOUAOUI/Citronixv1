package com.wora.citronix.service.impl;

import com.wora.citronix.DTO.Arbre.CreateArbreDTO;
import com.wora.citronix.DTO.Arbre.ResponseArbreDTO;
import com.wora.citronix.Entity.Arbre;
import com.wora.citronix.Entity.Champ;
import com.wora.citronix.Mapper.ArbreMapper;
import com.wora.citronix.repository.ArbreRepository;
import com.wora.citronix.repository.ChampsRepository;
import com.wora.citronix.service.ArbreService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArbreServiceImpl implements ArbreService {

    @Autowired
    private ArbreRepository arbreRepository;
    @Autowired
    private ArbreMapper arbreMapper;
    @Autowired
    private ChampsRepository champsRepository;

    @Override
    public ResponseArbreDTO createArbre(CreateArbreDTO createArbreDTO) {
        Arbre arbre = arbreMapper.toEntit(createArbreDTO);
        Champ champ = champsRepository.findById(createArbreDTO.getChamp_id()).orElseThrow(() -> new EntityNotFoundException("Champ does not exist"));
        arbre.setChamp(champ);
        Arbre savedArbre = arbreRepository.save(arbre);
        return arbreMapper.toResponse(savedArbre);
    }

    @Override
    public ResponseArbreDTO getArbreById(Long id) {
        Arbre arbre = arbreRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Arbre not found"));
        return arbreMapper.toResponse(arbre);
    }

    @Override
    public List<ResponseArbreDTO> getAllArbres() {
        List<Arbre> arbreList = arbreRepository.findAll();
        if (arbreList.isEmpty()){
            throw new RuntimeException("Arbre List is empty");
        }
        return arbreList.stream().map(arbreMapper::toResponse).toList();
    }

    @Override
    public boolean deleteArbre(Long id) {
        if (arbreRepository.existsById(id)){
            arbreRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ResponseArbreDTO updateArebre(CreateArbreDTO createArbreDTO) {
        return null;
    }
}
