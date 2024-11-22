package com.wora.citronix.DTO.DetailRecolt.embd;

import com.wora.citronix.DTO.Recolt.embd.EmbdRecoltDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmbdDetailRecoltForArbreDTO {
    private Double quantite;
    private LocalDate dateRecolte;
    private EmbdRecoltDTO recolt;
}
