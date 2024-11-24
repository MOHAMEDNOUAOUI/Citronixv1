package com.wora.citronix.service.impl;

import com.wora.citronix.DTO.Arbre.CreateArbreDTO;
import com.wora.citronix.DTO.Arbre.ResponseArbreDTO;
import com.wora.citronix.Entity.Arbre;
import com.wora.citronix.Entity.Champ;
import com.wora.citronix.Mapper.ArbreMapper;
import com.wora.citronix.helpers.ClassHelper;
import com.wora.citronix.repository.ArbreRepository;
import com.wora.citronix.repository.ChampsRepository;
import com.wora.citronix.service.ArbreService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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
    @Autowired
    private ClassHelper classHelper;

    @Override
    public ResponseArbreDTO createArbre(CreateArbreDTO createArbreDTO) {
        if (createArbreDTO.getDateDePlantation().getMonthValue() > 5 || createArbreDTO.getDateDePlantation().getMonthValue() < 3){
          throw new RuntimeException("you cant plant a tree before mars or after mai");
        }

        Arbre arbre = arbreMapper.toEntit(createArbreDTO);
        Champ champ = champsRepository.findById(createArbreDTO.getChamp_id()).orElseThrow(() -> new EntityNotFoundException("Champ does not exist"));
        Double totalArbreAllowable =(champ.getSuperficie() * 100) / 1000; // par m2

        if (champ.getArbreList().size() >= totalArbreAllowable) {
            throw new RuntimeException("You have exceeted the limit of Arbre");
        }

        arbre.setChamp(champ);
        Arbre savedArbre = arbreRepository.save(arbre);
        ResponseArbreDTO responseArbreDTO = arbreMapper.toResponse(savedArbre);
        String age = classHelper.calculateArbreAge(savedArbre);
        responseArbreDTO.setAge(age);
        return responseArbreDTO;
    }

    @Override
    public ResponseArbreDTO getArbreById(Long id) {
        Arbre arbre = arbreRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Arbre not found"));
        ResponseArbreDTO responseArbreDTO = arbreMapper.toResponse(arbre);
        String age = classHelper.calculateArbreAge(arbre);
        responseArbreDTO.setAge(age);
        return responseArbreDTO;
    }

    @Override
    public List<ResponseArbreDTO> getAllArbres() {
        List<Arbre> arbreList = arbreRepository.findAll();
        if (arbreList.isEmpty()){
            throw new RuntimeException("Arbre List is empty");
        }
        return arbreList.stream().map(arbre -> {
            ResponseArbreDTO responseArbreDTO = arbreMapper.toResponse(arbre);
            String age = classHelper.calculateArbreAge(arbre);
            responseArbreDTO.setAge(age);
            return responseArbreDTO;
        }).toList();
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
    public ResponseArbreDTO updateArebre(CreateArbreDTO createArbreDTO , Long id) {
        Arbre arbre = arbreRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Arbre not found"));
        classHelper.checkArbreData(createArbreDTO);
        classHelper.updateArbreData(arbre,createArbreDTO);
        Arbre savedArbre = arbreRepository.save(arbre);
        return arbreMapper.toResponse(savedArbre);
    }
}
