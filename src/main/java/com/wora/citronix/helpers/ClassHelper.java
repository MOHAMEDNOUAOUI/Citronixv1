package com.wora.citronix.helpers;

import com.wora.citronix.DTO.Arbre.ResponseArbreDTO;
import com.wora.citronix.DTO.Recolt.ResponseRecoltDTO;
import com.wora.citronix.DTO.Recolt.UpdateRecoltDTO;
import com.wora.citronix.Entity.Arbre;
import com.wora.citronix.Entity.Recolte;
import com.wora.citronix.service.RecolteService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

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

    public void checkRecoltData(UpdateRecoltDTO updateRecoltDTO , Long id){
        if (updateRecoltDTO.getDateRecolte() == null && updateRecoltDTO.getSaison() == null && updateRecoltDTO.getQuantiteTotal() == null){
            throw new RuntimeException("No data provided to be updated");
        }
    }

    public void updateRecoltData(Recolte recolte , UpdateRecoltDTO updateRecoltDTO){
        if (updateRecoltDTO.getQuantiteTotal() != null && !updateRecoltDTO.getQuantiteTotal().equals(recolte.getQuantiteTotal()) && updateRecoltDTO.getQuantiteTotal() >= 0){
            recolte.setQuantiteTotal(updateRecoltDTO.getQuantiteTotal());
        }
        if (updateRecoltDTO.getDateRecolte() != null && !updateRecoltDTO.getDateRecolte().equals(recolte.getDateRecolte())) {
            recolte.setDateRecolte(updateRecoltDTO.getDateRecolte());
        }

        if (updateRecoltDTO.getSaison() != null && !updateRecoltDTO.getSaison().equals(recolte.getSaison())) {
            recolte.setSaison(updateRecoltDTO.getSaison());
        }
    }
}
