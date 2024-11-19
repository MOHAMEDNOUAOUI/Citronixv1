package com.wora.citronix.DTO.Champ;

import com.wora.citronix.Entity.Arbre;
import com.wora.citronix.Entity.Ferme;
import com.wora.citronix.annotation.Exist.Exist;
import com.wora.citronix.repository.ArbreRepository;
import com.wora.citronix.repository.FermeRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateChampDTO {

    @NotNull
    @Positive
    private Double superficie;
    @Exist(entity = Ferme.class, repository = FermeRepository.class)
    private Long ferme_id;

}
