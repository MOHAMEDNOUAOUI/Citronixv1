package com.wora.citronix.DTO.Champ;

import com.wora.citronix.DTO.Arbre.embd.EmbdArbreDTO;
import com.wora.citronix.DTO.Ferme.emdb.EmbdFermeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseChampDTO {
    private Long id;
    private Double superficie;
    private EmbdFermeDTO ferme;
    private List<EmbdArbreDTO> arbreList;
}
