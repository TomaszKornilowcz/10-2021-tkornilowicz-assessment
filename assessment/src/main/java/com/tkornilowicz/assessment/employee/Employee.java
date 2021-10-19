package com.tkornilowicz.assessment.employee;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import com.tkornilowicz.assessment.department.Department;
import com.tkornilowicz.assessment.localization.Localization;
import com.tkornilowicz.assessment.position.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "employees")
@ToString
public class Employee implements Comparable<Employee> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CsvBindByPosition(position = 0)
    private Long id;
    @Column(name = "first_name", length = 30, nullable = false)
    @CsvBindByPosition(position = 1)
    private String firstName;
    @Column(name = "last_name", length = 30, nullable = false)
    @CsvBindByPosition(position = 2)
    private String lastName;
    @Column(length = 80, nullable = false, unique = true)
    @CsvBindByPosition(position = 3)
    private String email;
    @Column(name = "phone_number", nullable = false, unique = true)
    @CsvBindByPosition(position = 4)
    private Long phoneNumber;
    @Column(name = "employment_date", nullable = false)
    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByPosition(position = 5)
    private Date employmentDate;
    @Column(nullable = false)
    @CsvBindByPosition(position = 6)
    private Long salary;

    @ManyToOne
    @JoinColumn(name = "localization_id")
//    @CsvBindByPosition(position = 7)
    private Localization localization;

    @ManyToOne
    @JoinColumn(name = "department_id")
//    @CsvBindByPosition(position = 8)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "position_id")
//    @CsvBindByPosition(position = 9)
    private Position position;

    public Employee(
            String firstName,
            String lastName,
            String email,
            Long phoneNumber,
            Date employmentDate,
            Long salary,
            Localization localization,
            Department department,
            Position position
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.employmentDate = employmentDate;
        this.salary = salary;
        this.localization = localization;
        this.department = department;
        this.position = position;
    }

    @Override
    public int compareTo(Employee o) {
        if (getPosition().compareTo(o.getPosition()) == 0)
            return getEmploymentDate().compareTo(o.employmentDate);
        return getPosition().compareTo(o.getPosition());
    }
}
