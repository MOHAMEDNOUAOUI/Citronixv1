package com.wora.citronix.DTO.Arbre;

import com.wora.citronix.Entity.Champ;
import com.wora.citronix.Entity.DetailRecolte;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateArbreDTO {
    @Positive
    @NotNull
    private int age;
    private LocalDate dateDePlantation;
    @NotNull
    private Long champ_id;
}
