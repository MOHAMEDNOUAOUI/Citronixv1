package com.wora.citronix.Entity;

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
@Table(name = "ferme")
public class Ferme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false , name = "nom")
    private String nom;

    @Column(name = "localisation" , nullable = false)
    private String localisation;

    @Column(nullable = false , name = "superficie")
    private Double superficie;

    @Column(name = "date_de_creation" , nullable = false)
    private LocalDate dateDeCreation;

    @OneToMany(fetch = FetchType.EAGER , mappedBy = "ferme")
    private List<Champ> champsList;
}
