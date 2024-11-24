package com.wora.citronix.helpers;

import com.wora.citronix.DTO.Arbre.CreateArbreDTO;
import com.wora.citronix.DTO.Champ.CreateChampDTO;
import com.wora.citronix.DTO.Ferme.UpdateFermeDTO;
import com.wora.citronix.DTO.Recolt.UpdateRecoltDTO;
import com.wora.citronix.DTO.Vente.CreateVenteDTO;
import com.wora.citronix.Entity.*;
import com.wora.citronix.repository.FermeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.wora.citronix.repository.ChampsRepository;
import com.wora.citronix.repository.RecolteRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.Vector;

@Data
@Component
public class ClassHelper {

    @Autowired
    private FermeRepository fermeRepository;
    @Autowired
    private ChampsRepository champsRepository;
    @Autowired
    private RecolteRepository recolteRepository;


    public String calculateArbreAge(Arbre arbre) {
        Period agePeriod = Period.between(arbre.getDateDePlantation(), LocalDate.now());
        int Years = agePeriod.getYears();
        int Months = agePeriod.getMonths();
        int Days = agePeriod.getDays();
        String ageString = "";
        if (Years > 0){
            ageString += Years + " year" + (Years > 1 ? "s" : "");
        }
        if (Months > 0){
            if (!ageString.isEmpty()){
                ageString += ", ";
            }
            ageString +=Months + " month" + (Months > 1 ? "s" : "");
        }
        if (Days > 0){
            if (!ageString.isEmpty()){
                ageString += ", ";
            }
            ageString +=Days + " day" + (Days > 1 ? "s" : "");
        }

        if (ageString.isEmpty()){
            ageString = "Less than a day old";
        }

        return ageString;
    }


    // recolt

    public void checkRecoltData(UpdateRecoltDTO updateRecoltDTO ){
        if (updateRecoltDTO.getSaison() == null && updateRecoltDTO.getQuantiteTotal() == null){
            throw new RuntimeException("No data provided to be updated");
        }
    }

    public void checkFermeData(UpdateFermeDTO updateFermeDTO){
        if (updateFermeDTO.getLocalisation() == null && updateFermeDTO.getNom() == null && updateFermeDTO.getSuperficie() == null && updateFermeDTO.getDateDeCreation() == null){
            throw new RuntimeException("No data provided to be updated");
        }
    }

    public void checkChampData(CreateChampDTO createChampDTO){
        if (createChampDTO.getSuperficie() == null && createChampDTO.getFerme_id() == null){
            throw new RuntimeException("No data provided to be updated");
        }
    }

    public void checkArbreData(CreateArbreDTO createArbreDTO){
        if (createArbreDTO.getDateDePlantation() == null && createArbreDTO.getChamp_id() == null){
            throw new RuntimeException("No data provided to be updated");
        }
    }

    public void checkVenteData(CreateVenteDTO createVenteDTO){
        if (createVenteDTO.getDateVente() == null && createVenteDTO.getQuantity() == null && createVenteDTO.getPrixUnitaire() == null && createVenteDTO.getRecolte_id() == null && createVenteDTO.getClient() == null){
            throw new RuntimeException("No data provided to be updated");
        }
    }

    public void checkChampDataBeforeCreate(Champ champ){
        Ferme ferme = champ.getFerme();
        double allow = ferme.getSuperficie() / 2;
        double totalExistingSuperficie = ferme.getChampsList().stream()
                .mapToDouble(Champ::getSuperficie)
                .sum();

        if (ferme.getChampsList().size() >= 10) {
            throw new RuntimeException("Farm cannot have more than 10 champs");
        }

        if (allow < champ.getSuperficie()){
            throw new RuntimeException("champ ne peut dépasser "+ferme.getSuperficie()/2+" de la superficie totale de la ferme.");
        }

        if (ferme.getSuperficie() < totalExistingSuperficie + champ.getSuperficie()) {
            throw new RuntimeException("you have exceeted the limit , thers is only " + (ferme.getSuperficie() - totalExistingSuperficie) + " available");
        }
    }

    public void updateRecoltData(Recolte recolte , UpdateRecoltDTO updateRecoltDTO){
        Optional.ofNullable(updateRecoltDTO.getQuantiteTotal())
                .filter(quantity -> !quantity.equals(recolte.getQuantiteTotal()))
                .ifPresent(recolte::setQuantiteTotal);


        if (updateRecoltDTO.getSaison() != null && !updateRecoltDTO.getSaison().equals(recolte.getSaison())) {
            recolte.setSaison(updateRecoltDTO.getSaison());
        }
    }

    public void updateFermeData(Ferme ferme , UpdateFermeDTO updateFermeDTO){

            Optional.ofNullable(updateFermeDTO.getLocalisation())
                    .filter(localisation -> !localisation.equals(ferme.getLocalisation()))
                    .ifPresent(ferme::setLocalisation);

            Optional.ofNullable(updateFermeDTO.getSuperficie())
                    .filter(superficie -> !superficie.equals(ferme.getSuperficie()))
                    .ifPresent(superficie -> {
                        Double totalSuperifcierDesChamps = ferme.getChampsList().stream().mapToDouble(Champ::getSuperficie).sum();
                        if (totalSuperifcierDesChamps < superficie){
                            throw new RuntimeException("You can update a superficie : " + superficie + " because the total allocated superficie is :" + totalSuperifcierDesChamps + " and the total superficier is " + ferme.getSuperficie());
                        }
                        ferme.setSuperficie(superficie);
                    });

            Optional.ofNullable(updateFermeDTO.getDateDeCreation())
                    .filter(dateDeCreation -> !dateDeCreation.equals(ferme.getDateDeCreation()))
                    .ifPresent(dateDeCreation -> {
                        if (dateDeCreation.isAfter(LocalDate.now()) && dateDeCreation.isBefore(ferme.getDateDeCreation())){
                            throw new RuntimeException("You can change this date");
                        }
                        ferme.setDateDeCreation(dateDeCreation);
                    });
    }

    public void updateChamp(Champ champ , CreateChampDTO createChampDTO){


        Optional.ofNullable(createChampDTO.getSuperficie())
                .filter(superficie -> !superficie.equals(champ.getSuperficie()))
                .ifPresent(superficie -> {
                    Ferme ferme = champ.getFerme();
                    checkChampDataBeforeCreate(champ);
                    champ.setSuperficie(ferme.getSuperficie());
                });

        Optional.ofNullable(createChampDTO.getFerme_id())
                .filter(fermeId -> !fermeId.equals(champ.getId()))
                .ifPresent(fermeId -> {
                    checkChampDataBeforeCreate(champ);
                    Optional<Ferme> ferme = fermeRepository.findById(fermeId);
                    if (ferme.isPresent()){
                        champ.setFerme(ferme.get());
                    }else{
                        throw new EntityNotFoundException("Ferme doesnt exist");
                    }
                });

    }

    public void updateArbreData(Arbre arbre , CreateArbreDTO createArbreDTO){
        Optional.ofNullable(createArbreDTO.getDateDePlantation())
                .filter(arb -> !arb.equals(arbre.getDateDePlantation()))
                .ifPresent(arb -> {
                    arbre.setDateDePlantation(createArbreDTO.getDateDePlantation());
                });

        Optional.ofNullable(createArbreDTO.getChamp_id())
                .filter(champId -> !champId.equals(arbre.getId()))
                .ifPresent(champId -> {
                    Champ champ = champsRepository.findById(createArbreDTO.getChamp_id()).orElseThrow(() -> new EntityNotFoundException("Champ does not exist"));
                    Double totalArbreAllowable =(champ.getSuperficie() * 100) / 1000;

                    if (champ.getArbreList().size() >= totalArbreAllowable) {
                        throw new RuntimeException("You have exceeted the limit of Arbre");
                    }
                    arbre.setChamp(champ);
                });

    }

    public void updateVente(Vente vente , CreateVenteDTO createVenteDTO){

        Optional.ofNullable(createVenteDTO.getDateVente())
                .filter(dateVente -> !dateVente.equals(vente.getDateVente()))
                .ifPresent(dateVente -> {
                    LocalDate arbreRecoltDay = vente.getRecolte().getDetailRecoltes().stream()
                            .max(Comparator.comparing(DetailRecolte::getDateRecolte))
                            .map(DetailRecolte::getDateRecolte)
                            .orElse(null);

                    if (dateVente.isBefore(arbreRecoltDay)){
                        throw new RuntimeException("You cant sell before recolting");
                    }

                    vente.setDateVente(dateVente);
                });

        Optional.ofNullable(createVenteDTO.getPrixUnitaire())
                .filter(prixUnitaire -> !prixUnitaire.equals(vente.getPrixUnitaire()))
                .ifPresent(vente::setPrixUnitaire);

        Optional.ofNullable(createVenteDTO.getQuantity())
                .filter(quantity -> !quantity.equals(vente.getQuantity()))
                .ifPresent(vente::setQuantity);

        Optional.ofNullable(createVenteDTO.getClient().getFirstName())
                .filter(clientDTO -> !clientDTO.equals(vente.getClient().getFirstName()))
                .ifPresent(vente.getClient()::setFirstName);

        Optional.ofNullable(createVenteDTO.getClient().getLastName())
                .filter(clientDTO -> !clientDTO.equals(vente.getClient().getLastName()))
                .ifPresent(vente.getClient()::setLastName);

        Optional.ofNullable(createVenteDTO.getRecolte_id())
                .filter(recolteId -> vente.getRecolte() == null || !recolteId.equals(vente.getRecolte().getId()))
                .ifPresent(recolteId -> {
                    Recolte recolte = recolteRepository.findById(recolteId)
                            .orElseThrow(() -> new IllegalArgumentException("Recolte with ID " + recolteId + " not found"));
                    vente.setRecolte(recolte);
                });
    }
}
