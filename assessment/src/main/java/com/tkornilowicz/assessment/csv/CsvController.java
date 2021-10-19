package com.tkornilowicz.assessment.csv;

import com.tkornilowicz.assessment.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/csv")
public class CsvController {
    @Autowired
    private CsvService csvService;

    @PostMapping
    public ResponseEntity addEmployeesFromCsvFile(@RequestParam String path) throws FileNotFoundException {
        List<Employee> employees = csvService.readCsv(path);
        return new ResponseEntity(employees, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity writeBooksToCsvFile(@RequestParam String path) throws IOException {
        csvService.writeCsv(path);
        return new ResponseEntity("book has been saved", HttpStatus.OK);
    }
}
