package com.tkornilowicz.assessment.localization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;

@Repository
public interface LocalizationRepository extends JpaRepository<Localization, Long> {
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE localizations l SET " +
            "l.streetName = :newStreetName, " +
            "l.number = :newNumber, " +
            "l.zipCode = :newZipCode," +
            "l.city = :newCity WHERE l.id = :id")
    void updateLocalization(
            @Param(value = "id") Long id,
            @Param(value = "newStreetName") String streetName,
            @Param(value = "newNumber") String number,
            @Param(value = "newZipCode") String zipCode,
            @Param(value = "newCity") String City
    );
}
