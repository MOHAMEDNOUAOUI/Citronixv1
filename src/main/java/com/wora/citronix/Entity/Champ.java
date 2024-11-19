package com.wora.citronix.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
    @Min(1000)
    private Double superficie;

    @ManyToOne(fetch = FetchType.EAGER)
    private Ferme ferme;

    @OneToMany(mappedBy = "champ" , fetch = FetchType.EAGER)
    private List<Arbre> arbreList;
}
