package com.wora.citronix.helpers;

import com.wora.citronix.DTO.Arbre.ResponseArbreDTO;
import com.wora.citronix.DTO.Ferme.UpdateFermeDTO;
import com.wora.citronix.DTO.Recolt.ResponseRecoltDTO;
import com.wora.citronix.DTO.Recolt.UpdateRecoltDTO;
import com.wora.citronix.Entity.Arbre;
import com.wora.citronix.Entity.Champ;
import com.wora.citronix.Entity.Ferme;
import com.wora.citronix.Entity.Recolte;
import com.wora.citronix.service.RecolteService;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Data
@Component
@AllArgsConstructor
public class ClassHelper {



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

    public void updateRecoltData(Recolte recolte , UpdateRecoltDTO updateRecoltDTO){
        Optional.ofNullable(updateRecoltDTO.getQuantiteTotal())
                .filter(quantity -> !quantity.equals(recolte.getQuantiteTotal()))
                .ifPresent(recolte::setQuantiteTotal);


        if (updateRecoltDTO.getSaison() != null && !updateRecoltDTO.getSaison().equals(recolte.getSaison())) {
            recolte.setSaison(updateRecoltDTO.getSaison());
        }
    }

    private String nom;

    @Column(name = "localisation" , nullable = false)
    private String localisation;

    @Column(nullable = false , name = "superficie")
    private Double superficie;

    @Column(name = "date_de_creation" , nullable = false)
    private LocalDate dateDeCreation;

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
                    });
    }
}
