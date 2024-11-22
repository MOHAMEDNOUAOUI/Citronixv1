package com.wora.citronix.service.impl;

import com.wora.citronix.DTO.Arbre.ResponseArbreDTO;
import com.wora.citronix.DTO.DetailRecolt.CreateDetailRecoltDTO;
import com.wora.citronix.DTO.DetailRecolt.ResponseDetailRecoltDTO;
import com.wora.citronix.DTO.Recolt.ResponseRecoltDTO;
import com.wora.citronix.DTO.Recolt.UpdateRecoltDTO;
import com.wora.citronix.Entity.Arbre;
import com.wora.citronix.Entity.DetailRecolte;
import com.wora.citronix.Entity.Recolte;
import com.wora.citronix.Entity.emdb.DetailRecolteId;
import com.wora.citronix.Mapper.ArbreMapper;
import com.wora.citronix.Mapper.DetailRecoltMapper;
import com.wora.citronix.Mapper.RecoltMapper;
import com.wora.citronix.helpers.ClassHelper;
import com.wora.citronix.repository.ArbreRepository;
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
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class DetailRecoltServiceImpl implements DetailRecoltService {
    private final DetailRecoltMapper detailRecoltMapper;
    private final DetailRecolteRepository detailRecolteRepository;
    private final RecolteRepository recolteRepository;
    private final ArbreRepository arbreRepository;
    private final RecolteService recolteService;
    private final ClassHelper classHelper;

    @Override
    public ResponseDetailRecoltDTO Harvest(CreateDetailRecoltDTO createDetailRecoltDTO) {
        DetailRecolte detailRecolte = detailRecoltMapper.toEntity(createDetailRecoltDTO);

        Arbre arbre = arbreRepository.findById(createDetailRecoltDTO.getArbre_id()).orElseThrow(() -> new EntityNotFoundException("Arbre was not found"));

        LocalDate arbreRecoltDay = arbre.getDetailRecoltes().stream()
                .map(DetailRecolte::getRecolte)
                .max(Comparator.comparing(Recolte::getDateRecolte))
                .map(Recolte::getDateRecolte)
                .orElse(null);


        if (arbreRecoltDay == null || arbreRecoltDay.plusMonths(3).isBefore(LocalDate.now())) {
            // Proceed with the new harvest (Recolte)
            Recolte recolt = recolteRepository.findById(createDetailRecoltDTO.getRecolt_id())
                    .orElseThrow(() -> new EntityNotFoundException("Recolt was not found"));

            detailRecolte.setRecolte(recolt);
            detailRecolte.setArbre(arbre);

            DetailRecolteId ids = new DetailRecolteId();
            ids.setRecolteId(recolt.getId());
            ids.setArbreId(arbre.getId());
            detailRecolte.setId(ids);

            UpdateRecoltDTO updateRecoltDTO = new UpdateRecoltDTO();
            updateRecoltDTO.setQuantiteTotal(recolt.getQuantiteTotal() + createDetailRecoltDTO.getQuantite());
            recolteService.updateRecolt(updateRecoltDTO, recolt.getId());

            DetailRecolte savedDetailRecolt = detailRecolteRepository.save(detailRecolte);
            ResponseDetailRecoltDTO response = detailRecoltMapper.toResponse(savedDetailRecolt);

            String age = classHelper.calculateArbreAge(arbre);
            response.getArbre().setAge(age);

            return response;
        } else {
            Period periodBetweenLastRecoltAndNow = Period.between(arbreRecoltDay, LocalDate.now());
            long remainingMonths = 3 - periodBetweenLastRecoltAndNow.getMonths();
            long remainingDays = 30 - periodBetweenLastRecoltAndNow.getDays();

            throw new RuntimeException("You cannot harvest this Arbre yet. Remaining time: "
                    + remainingMonths + " month(s) and " + remainingDays + " day(s).");
        }
    }

    @Override
    public ResponseDetailRecoltDTO findHarvest(DetailRecolteId id) {
        DetailRecolte detailRecolte = detailRecolteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("There are not Details to be found"));
        ResponseDetailRecoltDTO response = detailRecoltMapper.toResponse(detailRecolte);
        String age = classHelper.calculateArbreAge(detailRecolte.getArbre());
        response.getArbre().setAge(age);
        return response;
    }

    @Override
    public List<ResponseDetailRecoltDTO> findHarvests() {
        List<DetailRecolte> details = detailRecolteRepository.findAll();
        return details.stream().map(detailRecolte -> {
            String age = classHelper.calculateArbreAge(detailRecolte.getArbre());
            ResponseDetailRecoltDTO response = detailRecoltMapper.toResponse(detailRecolte);
            response.getArbre().setAge(age);
            return response;
        }).toList();
    }


    @Override
    public boolean deleteHarvest(DetailRecolteId id) {
        DetailRecolte detailRecolte = detailRecolteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Detail recolt not found"));
        detailRecolteRepository.delete(detailRecolte);
        return true;
    }

    @Override
    public ResponseDetailRecoltDTO updateHarvest(CreateDetailRecoltDTO createDetailRecoltDTO ,DetailRecolteId ids) {
        return null;
    }

}
