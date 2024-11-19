package com.wora.citronix.repository;

import com.wora.citronix.Entity.Champ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampsRepository extends JpaRepository<Champ , Long> {
}
