package com.wora.citronix.DTO.Recolt;

import com.wora.citronix.Entity.Enum.Saison;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRecoltDTO {
    private LocalDate dateRecolte;
    private Double quantiteTotal;
    private Saison saison;
}
