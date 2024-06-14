package com.bharath.college_management.service.impl;

import com.bharath.college_management.entity.Department;
import com.bharath.college_management.repository.DepartmentRepository;
import com.bharath.college_management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> sortBasedOnDepartmentName(String depName) {
        List<Department> departments = departmentRepository.findAll(Sort.by(Sort.Direction.ASC, depName));
        return departments;
    }
}
