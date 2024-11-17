package com.wora.citronix.Entity;

import com.wora.citronix.Entity.emdb.DetailRecolteId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detail_recolte")
public class DetailRecolte {
    @EmbeddedId
    private DetailRecolteId id;

    @Column(nullable = false)
    private Double quantite;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("arbreId")
    @JoinColumn(name = "arbre_id")
    private Arbre arbre;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recolteId")
    @JoinColumn(name = "recolte_id")
    private Recolte recolte;
}