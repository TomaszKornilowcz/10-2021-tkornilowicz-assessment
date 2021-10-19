package com.tkornilowicz.assessment.employee;

import com.tkornilowicz.assessment.department.Department;
import com.tkornilowicz.assessment.department.DepartmentRepository;
import com.tkornilowicz.assessment.exception.DepartmentNotFoundException;
import com.tkornilowicz.assessment.exception.EmployeeNotFoundException;
import com.tkornilowicz.assessment.exception.LocalizationNotFoundException;
import com.tkornilowicz.assessment.exception.PositionNotFoundException;
import com.tkornilowicz.assessment.localization.Localization;
import com.tkornilowicz.assessment.localization.LocalizationRepository;
import com.tkornilowicz.assessment.position.Position;
import com.tkornilowicz.assessment.position.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private LocalizationRepository localizationRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private PositionRepository positionRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    public Employee addNewEmployee(Employee employeeToAdd) {
        return employeeRepository.save(employeeToAdd);
    }

    public Employee updateEmployee(Long id, Employee employeeToUpdate) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (!optionalEmployee.isPresent()) {
            throw new EmployeeNotFoundException(id);
        }

        Optional<Localization> optionalLocalization =
                localizationRepository.findById(employeeToUpdate.getLocalization().getId());


        if (!optionalLocalization.isPresent()) {
            throw new LocalizationNotFoundException(id);
        }

        Optional<Department> optionalDepartment =
                departmentRepository.findById(employeeToUpdate.getDepartment().getId());


        if (!optionalDepartment.isPresent()) {
            throw new DepartmentNotFoundException(id);
        }

        Optional<Position> optionalPosition =
                positionRepository.findById(employeeToUpdate.getPosition().getId());


        if (!optionalPosition.isPresent()) {
            throw new PositionNotFoundException(id);
        }

        Employee employee = optionalEmployee.get();
        Localization localization = optionalLocalization.get();
        Department department = optionalDepartment.get();
        Position position = optionalPosition.get();

        employeeRepository.updateEmployee(
                id,
                employeeToUpdate.getFirstName(),
                employeeToUpdate.getLastName(),
                employeeToUpdate.getEmail(),
                employeeToUpdate.getPhoneNumber(),
                employeeToUpdate.getEmploymentDate(),
                employeeToUpdate.getSalary(),
                localization,
                department,
                position
        );


        return employeeToUpdate;
    }

    public Employee deleteEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (!optionalEmployee.isPresent()) {
            throw new EmployeeNotFoundException(id);
        }

        Employee employeeToDelete = optionalEmployee.get();
        employeeRepository.delete(employeeToDelete);

        return employeeToDelete;
    }
}
