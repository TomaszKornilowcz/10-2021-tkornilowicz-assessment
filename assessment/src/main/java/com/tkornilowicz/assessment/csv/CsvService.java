package com.tkornilowicz.assessment.csv;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.tkornilowicz.assessment.department.Department;
import com.tkornilowicz.assessment.department.DepartmentRepository;
import com.tkornilowicz.assessment.employee.Employee;
import com.tkornilowicz.assessment.employee.EmployeeCsvModel;
import com.tkornilowicz.assessment.employee.EmployeeService;
import com.tkornilowicz.assessment.exception.DepartmentNotFoundException;
import com.tkornilowicz.assessment.exception.LocalizationNotFoundException;
import com.tkornilowicz.assessment.exception.PositionNotFoundException;
import com.tkornilowicz.assessment.localization.Localization;
import com.tkornilowicz.assessment.localization.LocalizationRepository;
import com.tkornilowicz.assessment.position.Position;
import com.tkornilowicz.assessment.position.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CsvService {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private LocalizationRepository localizationRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Employee> readCsv(String path) throws FileNotFoundException {
        List<EmployeeCsvModel> csvEmployees =
                new CsvToBeanBuilder(new FileReader(path.replace(" ", "\\")))
                        .withType(EmployeeCsvModel.class)
                        .build()
                        .parse();

        List<Employee> employees = mapCsvEmployeesModelToEmployee(csvEmployees);

        employees.forEach(employee -> employeeService.addNewEmployee(employee));

        return employees;
    }

    public List<Employee> mapCsvEmployeesModelToEmployee(List<EmployeeCsvModel> csvEmployees) {
        List<Employee> employees = new ArrayList<>();
        csvEmployees.forEach(csvEmployee -> {
            Optional<Localization> optionalLocalization =
                    localizationRepository.findById(csvEmployee.getLocalizationId());


            if (!optionalLocalization.isPresent()) {
                throw new LocalizationNotFoundException(csvEmployee.getLocalizationId());
            }

            Optional<Department> optionalDepartment =
                    departmentRepository.findById(csvEmployee.getDepartmentId());


            if (!optionalDepartment.isPresent()) {
                throw new DepartmentNotFoundException(csvEmployee.getDepartmentId());
            }

            Optional<Position> optionalPosition =
                    positionRepository.findById(csvEmployee.getPositionId());


            if (!optionalPosition.isPresent()) {
                throw new PositionNotFoundException(csvEmployee.getPositionId());
            }

            Localization localization = optionalLocalization.get();
            Department department = optionalDepartment.get();
            Position position = optionalPosition.get();

            employees.add(new Employee(
                    csvEmployee.getId(),
                    csvEmployee.getFirstName(),
                    csvEmployee.getLastName(),
                    csvEmployee.getEmail(),
                    csvEmployee.getPhoneNumber(),
                    csvEmployee.getEmploymentDate(),
                    csvEmployee.getSalary(),
                    localization,
                    department,
                    position
            ));
        });

        return employees;
    }

    public List<EmployeeCsvModel> mapEmployeeToCsvModel() {
        List<EmployeeCsvModel> employeeCsvModels = new ArrayList<>();
        List<Employee> employees = employeeService.getAllEmployees();
        employees.forEach(employee -> employeeCsvModels.add(new EmployeeCsvModel(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getEmploymentDate(),
                employee.getSalary(),
                employee.getLocalization().getId(),
                employee.getDepartment().getId(),
                employee.getPosition().getId()
        )));

        return employeeCsvModels;
    }

    public void writeCsv(String path) throws IOException {
        Writer writer = new FileWriter(path.replace(" ", "\\"));

        StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build();


        List<EmployeeCsvModel> employeeCsvModels = mapEmployeeToCsvModel();
        try {
            sbc.write(employeeCsvModels);
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
        writer.close();
    }
}
