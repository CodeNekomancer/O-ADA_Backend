package com.github.CodeNekomancer.OADA_Backend.persistence.repository;

import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.ADAcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ADAccRepository extends JpaRepository<ADAcc, Long> {
    Optional<ADAcc> findByUsername(String username);
}
