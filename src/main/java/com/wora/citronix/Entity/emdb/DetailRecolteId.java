package com.wora.citronix.Entity.emdb;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailRecolteId implements Serializable {
    @Column(name = "arbre_id")
    private Long arbreId;

    @Column(name = "recolte_id")
    private Long recolteId;
}