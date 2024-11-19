package com.wora.citronix.repository;

import com.wora.citronix.Entity.DetailRecolte;
import com.wora.citronix.Entity.emdb.DetailRecolteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRecolteRepository extends JpaRepository<DetailRecolte , DetailRecolteId> {
}
