package com.tkornilowicz.assessment.employee;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCsvModel {
    @CsvBindByPosition(position = 0)
    private Long id;
    @CsvBindByPosition(position = 1)
    private String firstName;
    @CsvBindByPosition(position = 2)
    private String lastName;
    @CsvBindByPosition(position = 3)
    private String email;
    @CsvBindByPosition(position = 4)
    private Long phoneNumber;
    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByPosition(position = 5)
    private Date employmentDate;
    @CsvBindByPosition(position = 6)
    private Long salary;
    @CsvBindByPosition(position = 7)
    private Long localizationId;
    @CsvBindByPosition(position = 8)
    private Long departmentId;
    @CsvBindByPosition(position = 9)
    private Long positionId;
}
