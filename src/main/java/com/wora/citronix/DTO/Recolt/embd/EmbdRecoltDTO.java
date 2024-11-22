package com.wora.citronix.DTO.Recolt.embd;

import com.wora.citronix.Entity.Enum.Saison;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmbdRecoltDTO {
    private Long id;
    private Double quantiteTotal;
    private Saison saison;
}
