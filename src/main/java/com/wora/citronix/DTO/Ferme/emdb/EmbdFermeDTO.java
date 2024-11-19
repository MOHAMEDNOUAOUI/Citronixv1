package com.wora.citronix.DTO.Ferme.emdb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmbdFermeDTO {
    private Long id;
    private String nom;
    private String localisation;
    private Double superficie;
    private LocalDate dateDeCreation;
}
