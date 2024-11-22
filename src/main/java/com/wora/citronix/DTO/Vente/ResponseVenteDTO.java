package com.wora.citronix.DTO.Vente;

import com.wora.citronix.DTO.Recolt.embd.EmbdRecoltDTO;
import com.wora.citronix.DTO.Vente.embdClient.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVenteDTO {
    private Long id;
    private LocalDate dateVente;
    private Double prixUnitaire;
    private Double quantity;
    private ClientDTO client;
    private EmbdRecoltDTO recolte;
}
