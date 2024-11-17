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
@Table(name = "arbre")
public class Arbre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false , name = "age")
    private int age;

    @Column(name = "date_de_plantation" , nullable = false)
    private LocalDate dateDePlantation;

    @ManyToMany(fetch = FetchType.EAGER)
    private Champ champ;

    @OneToMany(mappedBy = "arbre", cascade = CascadeType.ALL)
    private List<DetailRecolte> detailRecoltes;
}
