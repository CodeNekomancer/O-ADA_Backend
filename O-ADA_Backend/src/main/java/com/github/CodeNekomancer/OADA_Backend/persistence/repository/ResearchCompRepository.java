package com.github.CodeNekomancer.OADA_Backend.persistence.repository;

import com.github.CodeNekomancer.OADA_Backend.model.ResearchComp.ResearchComp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearchCompRepository extends JpaRepository<ResearchComp, Long> {
}
