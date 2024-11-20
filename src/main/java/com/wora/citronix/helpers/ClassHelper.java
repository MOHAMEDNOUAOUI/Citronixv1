package com.wora.citronix.helpers;

import com.wora.citronix.DTO.Arbre.ResponseArbreDTO;
import com.wora.citronix.Entity.Arbre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Data
@Component
public class ClassHelper {

    public void calculateArbreAge(ResponseArbreDTO response , Arbre arbre) {
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
        response.setAge(ageString);
    }
}
