package com.wora.citronix.service.impl;

import com.wora.citronix.DTO.Arbre.ResponseArbreDTO;
import com.wora.citronix.DTO.DetailRecolt.CreateDetailRecoltDTO;
import com.wora.citronix.DTO.DetailRecolt.ResponseDetailRecoltDTO;
import com.wora.citronix.DTO.Recolt.ResponseRecoltDTO;
import com.wora.citronix.Entity.Arbre;
import com.wora.citronix.Entity.DetailRecolte;
import com.wora.citronix.Entity.Recolte;
import com.wora.citronix.Mapper.ArbreMapper;
import com.wora.citronix.Mapper.DetailRecoltMapper;
import com.wora.citronix.Mapper.RecoltMapper;
import com.wora.citronix.repository.DetailRecolteRepository;
import com.wora.citronix.repository.RecolteRepository;
import com.wora.citronix.service.ArbreService;
import com.wora.citronix.service.DetailRecoltService;
import com.wora.citronix.service.RecolteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.relation.RelationNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@AllArgsConstructor
public class DetailRecoltServiceImpl implements DetailRecoltService {
    private final DetailRecoltMapper detailRecoltMapper;
    private final DetailRecolteRepository detailRecolteRepository;
    private final RecolteService recolteService;
    private final ArbreService arbreService;
    private final ArbreMapper arbreMapper;
    private final RecoltMapper recoltMapper;

    @Override
    public ResponseDetailRecoltDTO Harvest(CreateDetailRecoltDTO createDetailRecoltDTO) {
        DetailRecolte detailRecolte = detailRecoltMapper.toEntity(createDetailRecoltDTO);
        ResponseRecoltDTO recolt = recolteService.findRecoltById(createDetailRecoltDTO.getRecolt_id());
        ResponseArbreDTO arbre = arbreService.getArbreById(createDetailRecoltDTO.getArbre_id());
        LocalDate recoltedDay = recolt.getDateRecolte();
        Period periodbetwenRecolteDayandToday = Period.between(recoltedDay , LocalDate.now());
        if (recoltedDay.plusMonths(3).isBefore(LocalDate.now())){
            throw new RuntimeException("You can recolt this Arbre before the next season , remaining time : " + periodbetwenRecolteDayandToday.getChronology());
        }
        Recolte recolte =recoltMapper.toEntityFromResponse(recolt);
        Arbre arbre1 = arbreMapper.toEntityFromResponse(arbre);
        detailRecolte.setRecolte(recolte);
        detailRecolte.setArbre(arbre1);
        DetailRecolte savedDetailRecolt = detailRecolteRepository.save(detailRecolte);
        ResponseDetailRecoltDTO response = detailRecoltMapper.toResponse(savedDetailRecolt);
        return response;
    }

    @Override
    public ResponseDetailRecoltDTO findHarvest(Long id) {
        return null;
    }

    @Override
    public List<ResponseDetailRecoltDTO> findHarvests() {
        return List.of();
    }

    @Override
    public ResponseDetailRecoltDTO updateHarvest(CreateDetailRecoltDTO createDetailRecoltDTO) {
        return null;
    }

    @Override
    public boolean deleteHarvest(Long id) {
        return false;
    }
}
