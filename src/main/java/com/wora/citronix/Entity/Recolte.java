package com.wora.citronix.Entity;

import com.wora.citronix.Entity.Enum.Saison;
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
@Table(name = "recolte")
public class Recolte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date_recolte" , nullable = false)
    private LocalDate dateRecolte;

    @Column(nullable = false , name = "quantiteTotal")
    private Double quantiteTotal;

    @Column(name = "saison" , nullable = false)
    private Saison saison;

    @OneToMany(mappedBy = "recolte" , fetch = FetchType.EAGER)
    private List<Vente> venteList;

    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<DetailRecolte> detailRecoltes;
}
