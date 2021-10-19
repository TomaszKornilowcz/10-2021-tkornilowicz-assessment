package com.tkornilowicz.assessment.salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AverageSalaryModel {
    private String position;
    private int seniority;
    private double AvgSalary;
}
