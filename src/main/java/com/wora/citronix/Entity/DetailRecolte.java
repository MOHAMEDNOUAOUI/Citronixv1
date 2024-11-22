package com.wora.citronix.Entity;

import com.wora.citronix.Entity.emdb.DetailRecolteId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @Column(name = "date_recolte" , nullable = false)
    private LocalDate dateRecolte;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("arbreId")
    private Arbre arbre;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("recolteId")
    private Recolte recolte;
}