package com.wora.citronix.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "champ")
public class Champ {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false , name = "superficie")
    private Double superficie;

    @ManyToOne(fetch = FetchType.EAGER)
    private Ferme ferme;

    @OneToMany(mappedBy = "champ" , fetch = FetchType.EAGER)
    private List<Arbre> arbreList;
}
