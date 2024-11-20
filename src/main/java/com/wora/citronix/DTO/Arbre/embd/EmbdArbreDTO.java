package com.wora.citronix.DTO.Arbre.embd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmbdArbreDTO {
    private Long id;
    private String age;
    private LocalDate dateDePlantation;
    //detail recolts
}
