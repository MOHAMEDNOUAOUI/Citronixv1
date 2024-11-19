package com.wora.citronix.repository.CriteriaBuilder.impl;

import com.wora.citronix.Entity.Ferme;
import com.wora.citronix.repository.CriteriaBuilder.FermeCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FermeCriteriaImpl implements FermeCriteria {

    @Autowired
    private EntityManager em;

    @Override
    public List<Ferme> findByNameAndLocalisation(String name, String localisation) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Ferme> criteriaQuery = criteriaBuilder.createQuery(Ferme.class);

        Root<Ferme> fermeRoot = criteriaQuery.from(Ferme.class);

        Predicate namePredicate = criteriaBuilder.equal(fermeRoot.get("name") , name);
        Predicate localisationPredicate = criteriaBuilder.equal(fermeRoot.get("localisation") , localisation);

        criteriaQuery.select(fermeRoot)
                .where(criteriaBuilder.and(namePredicate,localisationPredicate));
        return em.createQuery(criteriaQuery).getResultList();
    }
}
