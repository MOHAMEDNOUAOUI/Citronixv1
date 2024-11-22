package com.wora.citronix.DTO.Recolt;

import com.wora.citronix.Entity.DetailRecolte;
import com.wora.citronix.Entity.Enum.Saison;
import com.wora.citronix.Entity.Vente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRecoltDTO {
    private Long id;
    private Double quantiteTotal;
    private Saison saison;
//    private List<Vente> venteList;
//    private List<DetailRecolte> detailRecoltes;
}
