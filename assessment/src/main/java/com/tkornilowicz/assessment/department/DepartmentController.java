package com.tkornilowicz.assessment.department;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();

        return new ResponseEntity(departments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addNewDepartment(@RequestBody Department departmentToAdd) {
        try {
            Department newDepartment = departmentService.addNewDepartment(departmentToAdd);
            return new ResponseEntity(newDepartment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("cannot create a department", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity updateDepartment (
            @PathVariable Long id,
            @RequestBody Department departmentToUpdate) {
        try {
            Department updatedDepartment = departmentService.updateDepartment(
                    id,
                    departmentToUpdate
            );
            return new ResponseEntity(updatedDepartment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteDepartment (@PathVariable Long id) {
        try {
            Department deletedDepartment = departmentService.deleteDepartment(id);
            return new ResponseEntity(deletedDepartment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
