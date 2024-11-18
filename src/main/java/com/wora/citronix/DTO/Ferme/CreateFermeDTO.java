package com.wora.citronix.DTO.Ferme;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFermeDTO {

    @NotBlank
    private String nom;
    @NotBlank
    private String localisation;
    @Positive
    @NotNull
    private Double superficie;
    @NotNull
    private LocalDate dateDeCreation;

}
