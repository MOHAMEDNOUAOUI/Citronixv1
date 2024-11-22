package com.wora.citronix.Entity;

import com.wora.citronix.Entity.emdb.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vente")
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false , name = "date_vente")
    private LocalDate dateVente;

    private Double prixUnitaire;

    private Double quantity;

    @Embedded
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    private Recolte recolte;
}
