package com.wora.citronix.DTO.Ferme;

import com.wora.citronix.DTO.Champ.embd.EmdbChampDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFermeDTO {
    private String nom;
    private String localisation;
    private Double superficie;
    private LocalDate dateDeCreation;
}
