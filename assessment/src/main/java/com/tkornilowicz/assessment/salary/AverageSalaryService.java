package com.tkornilowicz.assessment.salary;

import com.tkornilowicz.assessment.date.DateService;
import com.tkornilowicz.assessment.employee.Employee;
import com.tkornilowicz.assessment.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AverageSalaryService {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DateService dateService;

    public List<AverageSalaryModel> countAverageSalary() {
        List<AverageSalaryModel> averageSalaryModels = new ArrayList<>();
        List<Employee> employees = employeeService.getAllEmployees();
        employees.sort(Employee::compareTo);

        Long avgSalary = employees.get(0).getSalary();
        int counter = 1;
        for (int i = 1; i < employees.size(); ++i) {
            Employee firstEmployee = employees.get(i - 1);
            Employee secondEmployee = employees.get(i);
            if (dateService.countSeniority(firstEmployee) == dateService.countSeniority(secondEmployee)) {
                if (firstEmployee.getPosition().equals(secondEmployee.getPosition())) {
                    avgSalary += secondEmployee.getSalary();
                    counter++;
                } else {
                    averageSalaryModels.add(new AverageSalaryModel(
                            firstEmployee.getPosition().getName(),
                            dateService.countSeniority(firstEmployee),
                            avgSalary / counter)
                    );
                    avgSalary = secondEmployee.getSalary();
                    counter = 1;
                }
            } else {
                averageSalaryModels.add(new AverageSalaryModel(
                        firstEmployee.getPosition().getName(),
                        dateService.countSeniority(firstEmployee),
                        avgSalary / counter)
                );
                avgSalary = secondEmployee.getSalary();
                counter = 1;
            }
        }
        if (counter >= 1) {
            averageSalaryModels.add(new AverageSalaryModel(
                    employees.get(employees.size()-1).getPosition().getName(),
                    dateService.countSeniority(employees.get(employees.size()-1)),
                    avgSalary / counter)
            );
        }
        return averageSalaryModels;
    }

}
