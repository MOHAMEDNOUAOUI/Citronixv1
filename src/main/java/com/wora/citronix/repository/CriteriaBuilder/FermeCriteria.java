package com.wora.citronix.repository.CriteriaBuilder;

import com.wora.citronix.Entity.Ferme;

import java.util.List;

public interface FermeCriteria {
    List<Ferme> findByNameAndLocalisation(String name,String localisation);
}
