package com.wora.citronix.repository;

import com.wora.citronix.Entity.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenteRepository extends JpaRepository<Vente, Long> {
}
