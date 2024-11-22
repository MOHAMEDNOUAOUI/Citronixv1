package com.wora.citronix.DTO.Recolt;


import com.wora.citronix.Entity.Enum.Saison;
import com.wora.citronix.annotation.TodayDate.TodayDate;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecoltDTO {
    @NotNull
    @Min(0)
    private Double quantiteTotal = 0.0;
    @NotNull
    private Saison saison;
}
