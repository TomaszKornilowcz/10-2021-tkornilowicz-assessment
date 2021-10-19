package com.tkornilowicz.assessment.position;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE positions p SET p.name = :newName WHERE p.id = :id")
    void updatePosition(@Param(value = "id") Long id, @Param(value = "newName") String name);
}
