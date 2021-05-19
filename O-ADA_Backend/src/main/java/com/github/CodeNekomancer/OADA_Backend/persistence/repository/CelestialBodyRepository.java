package com.github.CodeNekomancer.OADA_Backend.persistence.repository;

import com.github.CodeNekomancer.OADA_Backend.model.CelestialBody.CelestialBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CelestialBodyRepository extends JpaRepository<CelestialBody, Long> {
}
