package com.wora.citronix.DTO.DetailRecolt;


import com.wora.citronix.DTO.Arbre.embd.EmbdArbreDTO;
import com.wora.citronix.DTO.Recolt.embd.EmbdRecoltDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDetailRecoltDTO {
    private Double quantite;
    private EmbdArbreDTO arbre;
    private EmbdRecoltDTO recolte;
}
