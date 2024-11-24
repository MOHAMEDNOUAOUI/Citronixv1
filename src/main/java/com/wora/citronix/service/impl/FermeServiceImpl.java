package com.wora.citronix.service.impl;

import com.wora.citronix.DTO.Ferme.CreateFermeDTO;
import com.wora.citronix.DTO.Ferme.ResponseFermeDTO;
import com.wora.citronix.DTO.Ferme.SearchFermeDTO;
import com.wora.citronix.DTO.Ferme.UpdateFermeDTO;
import com.wora.citronix.Entity.Ferme;
import com.wora.citronix.Mapper.FermeMapper;
import com.wora.citronix.helpers.ClassHelper;
import com.wora.citronix.repository.CriteriaBuilder.FermeCriteria;
import com.wora.citronix.repository.FermeRepository;
import com.wora.citronix.service.FermeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FermeServiceImpl implements FermeService {

    @Autowired
    private FermeRepository fermeRepository;

    @Autowired
    private FermeCriteria fermeCriteria;

    @Autowired
    private FermeMapper fermeMapper;
    @Autowired
    private ClassHelper classHelper;

    @Override
    public ResponseFermeDTO createFerme(CreateFermeDTO createFermeDTO) {
        Ferme ferme = fermeMapper.toEntity(createFermeDTO);
        Ferme saveFerme = fermeRepository.save(ferme);
        return fermeMapper.toResponse(saveFerme);
    }

    @Override
    public List<ResponseFermeDTO> getAllFermes() {
        List<Ferme> fermes = fermeRepository.findAll();
        if (fermes.isEmpty()){
            throw new RuntimeException("The are no fermes yet");
        }
        return fermes.stream().map(fermeMapper::toResponse).toList();
    }

    @Override
    public List<ResponseFermeDTO> getAllFermesByNameAndLocalisation(SearchFermeDTO searchFermeDTO) {
        List<Ferme> fermes = fermeCriteria.findByNameAndLocalisation(searchFermeDTO.getNom() , searchFermeDTO.getLocalisation());
        if (fermes.isEmpty()){
            throw new RuntimeException("The are no fermes yet");
        }
        return fermes.stream().map(fermeMapper::toResponse).toList();
    }


    @Override
    public ResponseFermeDTO getFermeById(Long id) {
        Ferme ferme = fermeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ferme not found"));
        return fermeMapper.toResponse(ferme);
    }

    @Override
    public boolean removeFerme(Long id) {
        Optional<Ferme> ferme = fermeRepository.findById(id);
        if (ferme.isPresent()){
            fermeRepository.deleteById(id);
            return true;
        }
        else {
            throw new EntityNotFoundException("Ferme not found");
        }
    }

    @Override
    public ResponseFermeDTO updateFerme(UpdateFermeDTO updateFermeDTO, Long id) {
        Ferme ferme = fermeRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Ferme not found"));
        classHelper.checkFermeData(updateFermeDTO);
        classHelper.updateFermeData(ferme, updateFermeDTO);
        fermeRepository.save(ferme);
        return fermeMapper.toResponse(ferme);
    }
}
