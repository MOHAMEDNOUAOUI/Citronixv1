package com.wora.citronix.DTO.Arbre;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseArbreDTO {
    private Long id;
    private int age;
    private LocalDate dateDePlantation;
}
