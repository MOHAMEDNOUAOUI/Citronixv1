package com.wora.citronix.DTO.DetailRecolt;


import com.wora.citronix.DTO.Arbre.embd.EmbdArbreDTO;
import com.wora.citronix.DTO.Recolt.embd.EmbdRecoltDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDetailRecoltDTO {
    private Double quantite;
    private LocalDate dateRecolte;
    private EmbdArbreDTO arbre;
    private EmbdRecoltDTO recolte;
}
