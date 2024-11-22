package com.wora.citronix.DTO.Vente;

import com.wora.citronix.DTO.Vente.embdClient.ClientDTO;
import com.wora.citronix.Entity.Recolte;
import com.wora.citronix.annotation.Exist.Exist;
import com.wora.citronix.repository.RecolteRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVenteDTO {
    @NotNull
    private LocalDate dateVente;
    @NotNull
    private Double prixUnitaire;
    @NotNull
    private Double quantity;
    private ClientDTO client;
    @NotNull
    @Exist(message = "Recolt does not exist" , entity = Recolte.class , repository = RecolteRepository.class)
    private Long recolte_id;
}
