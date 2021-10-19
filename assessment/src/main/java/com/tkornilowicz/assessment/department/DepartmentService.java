package com.tkornilowicz.assessment.department;

import com.tkornilowicz.assessment.exception.DepartmentNotFoundException;
import com.tkornilowicz.assessment.exception.LocalizationNotFoundException;
import com.tkornilowicz.assessment.localization.Localization;
import com.tkornilowicz.assessment.localization.LocalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private LocalizationRepository localizationRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department addNewDepartment(Department departmentToAdd) {
        return departmentRepository.save(departmentToAdd);
    }


    public Department updateDepartment(Long id, Department departmentToUpdate) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        if (!optionalDepartment.isPresent()) {
            throw new DepartmentNotFoundException(id);
        }

        Optional<Localization> optionalLocalization =
                localizationRepository.findById(departmentToUpdate.getLocalization().getId());


        if (!optionalLocalization.isPresent()) {
            throw new LocalizationNotFoundException(id);
        }

        Department department = optionalDepartment.get();
        Localization localization = optionalLocalization.get();

        departmentRepository.updateDepartment(
                id,
                departmentToUpdate.getName(),
                localization
        );


        return departmentToUpdate;
    }

    public Department deleteDepartment(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        if (!optionalDepartment.isPresent()) {
            throw new DepartmentNotFoundException(id);
        }

        Department departmentToDelete = optionalDepartment.get();
        departmentRepository.delete(departmentToDelete);

        return departmentToDelete;
    }
}
