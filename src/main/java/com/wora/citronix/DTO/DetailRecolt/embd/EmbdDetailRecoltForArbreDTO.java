package com.wora.citronix.DTO.DetailRecolt.embd;

import com.wora.citronix.DTO.Recolt.embd.EmbdRecoltDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmbdDetailRecoltForArbreDTO {
    private Double quantite;
    private EmbdRecoltDTO recolt;
}
