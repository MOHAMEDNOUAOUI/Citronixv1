package com.wora.citronix.DTO.Vente.embdClient;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
}
