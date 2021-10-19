package com.tkornilowicz.assessment.date;

import com.tkornilowicz.assessment.employee.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DateService {
    public LocalDateTime getCurrentDate() {
        return LocalDateTime.now();
    }

    public int countSeniority(Employee employee) {
        int employeeEmploymentDate = employee.getEmploymentDate().getYear() + 1900;
        return getCurrentDate().getYear() - employeeEmploymentDate;
    }
}
