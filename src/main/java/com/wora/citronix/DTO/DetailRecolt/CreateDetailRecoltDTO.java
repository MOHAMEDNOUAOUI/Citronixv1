package com.wora.citronix.DTO.DetailRecolt;

import com.wora.citronix.Entity.Arbre;
import com.wora.citronix.Entity.Recolte;
import com.wora.citronix.annotation.Exist.Exist;
import com.wora.citronix.annotation.TodayDate.TodayDate;
import com.wora.citronix.repository.ArbreRepository;
import com.wora.citronix.repository.RecolteRepository;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDetailRecoltDTO {
    @TodayDate
    private LocalDate dateRecolte = LocalDate.now();
    @NotNull
    @Min(0)
    private Double quantite;
    @Exist(repository = ArbreRepository.class , entity = Arbre.class , message = "Arbre with this id does not exist")
    private Long arbre_id;
    @Exist(repository = RecolteRepository.class, entity = Recolte.class ,message = "Recolt does not exist with this id")
    private Long recolt_id;
}
