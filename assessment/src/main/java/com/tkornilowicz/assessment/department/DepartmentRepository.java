package com.tkornilowicz.assessment.department;

import com.tkornilowicz.assessment.localization.Localization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE departments d SET " +
            "d.name = :newName, " +
            "d.localization = :newLocalization" +
            " WHERE d.id = :id")
    void updateDepartment(
            @Param(value = "id") Long id,
            @Param(value = "newName") String name,
            @Param(value = "newLocalization") Localization localization
    );
}
