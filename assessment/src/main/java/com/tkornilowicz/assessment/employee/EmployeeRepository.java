package com.tkornilowicz.assessment.employee;

import com.tkornilowicz.assessment.department.Department;
import com.tkornilowicz.assessment.localization.Localization;
import com.tkornilowicz.assessment.position.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE employees e SET " +
            "e.firstName = :newFirstName, " +
            "e.lastName = :newLastName, " +
            "e.email = :newEmail, " +
            "e.phoneNumber = :newPhoneNumber, " +
            "e.employmentDate = :newEmploymentDate, " +
            "e.salary = :newSalary, " +
            "e.localization = :newLocalization, " +
            "e.department = :newDepartment, " +
            "e.position = :newPosition" +
            " WHERE e.id = :id")
    void updateEmployee(
            @Param(value = "id") Long id,
            @Param(value = "newFirstName") String firstName,
            @Param(value = "newLastName") String lastName,
            @Param(value = "newEmail") String email,
            @Param(value = "newPhoneNumber") Long phoneNumber,
            @Param(value = "newEmploymentDate") Date employmentDate,
            @Param(value = "newSalary") Long salary,
            @Param(value = "newLocalization") Localization localization,
            @Param(value = "newDepartment") Department department,
            @Param(value = "newPosition") Position position
    );
}
