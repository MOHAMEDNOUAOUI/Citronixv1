package com.wora.citronix.DTO.Ferme;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchFermeDTO {
    private String nom;
    private String localisation;
}
