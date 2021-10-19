package com.tkornilowicz.assessment.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();

        return new ResponseEntity(employees, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addNewEmployee(@RequestBody Employee employeeToAdd) {
        try {
            Employee newEmployee = employeeService.addNewEmployee(employeeToAdd);
            return new ResponseEntity(newEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("cannot create a employee", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employeeToUpdate) {
        try {
            Employee updatedEmployee = employeeService.updateEmployee(
                    id,
                    employeeToUpdate
            );
            return new ResponseEntity(updatedEmployee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        try {
            Employee deletedEmployee = employeeService.deleteEmployee(id);
            return new ResponseEntity(deletedEmployee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
