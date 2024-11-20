package com.wora.citronix.service.impl;

import com.wora.citronix.DTO.Recolt.CreateRecoltDTO;
import com.wora.citronix.DTO.Recolt.ResponseRecoltDTO;
import com.wora.citronix.DTO.Recolt.UpdateRecoltDTO;
import com.wora.citronix.Entity.Recolte;
import com.wora.citronix.Mapper.RecoltMapper;
import com.wora.citronix.helpers.ClassHelper;
import com.wora.citronix.repository.RecolteRepository;
import com.wora.citronix.service.RecolteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RecoltServiceImpl implements RecolteService {
    private final RecolteRepository recolteRepository;
    private final RecoltMapper recoltMapper;
    private final ClassHelper classHelper;


    @Override
    public ResponseRecoltDTO createRecolt(CreateRecoltDTO createRecoltDTO) {
        Recolte recolte = recoltMapper.toEntity(createRecoltDTO);
        ResponseRecoltDTO response = recoltMapper.toResponse(recolteRepository.save(recolte));
        return response;
    }

    @Override
    public ResponseRecoltDTO findRecoltById(Long id) {
        Recolte recolte = recolteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Recolt not found"));
        ResponseRecoltDTO response = recoltMapper.toResponse(recolte);
        return response;
    }

    @Override
    public List<ResponseRecoltDTO> getAllRecolts() {
        List<Recolte> recoltes = recolteRepository.findAll();
        if (recoltes.isEmpty()){
            throw new EntityNotFoundException("No Recolts found ");
        }
        return recoltes.stream().map(recoltMapper::toResponse).toList();
    }

    @Override
    public boolean deleteRecolt(Long id) {
        Recolte recolte = recolteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Recolt not found"));
        recolteRepository.delete(recolte);
        return true;
    }

    @Override
    public ResponseRecoltDTO updateRecolt(UpdateRecoltDTO updateRecoltDTO, Long id) {
        Recolte recolte = recolteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Recolt was not found"));
        classHelper.checkRecoltData(updateRecoltDTO,id);
        classHelper.updateRecoltData(recolte,updateRecoltDTO);
        recolteRepository.save(recolte);
        return recoltMapper.toResponse(recolte);
    }


}
