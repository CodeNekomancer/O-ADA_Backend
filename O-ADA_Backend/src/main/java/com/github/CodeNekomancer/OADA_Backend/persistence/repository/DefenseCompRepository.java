package com.github.CodeNekomancer.OADA_Backend.persistence.repository;

import com.github.CodeNekomancer.OADA_Backend.model.DefenseComp.DefenseComp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefenseCompRepository extends JpaRepository<DefenseComp, Long> {
}
