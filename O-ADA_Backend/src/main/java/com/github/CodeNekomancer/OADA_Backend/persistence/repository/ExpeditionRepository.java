package com.github.CodeNekomancer.OADA_Backend.persistence.repository;

import com.github.CodeNekomancer.OADA_Backend.model.Expedition.Expedition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpeditionRepository extends JpaRepository<Expedition, Long> {
}
