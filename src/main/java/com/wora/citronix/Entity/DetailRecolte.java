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

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("arbreId")
    private Arbre arbre;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("recolteId")
    private Recolte recolte;
}