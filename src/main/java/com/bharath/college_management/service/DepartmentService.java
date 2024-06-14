package com.bharath.college_management.service;

import com.bharath.college_management.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);
    List<Department> getAllDepartments();

    List<Department> sortBasedOnDepartmentName(String depName);
}

