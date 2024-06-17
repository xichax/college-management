package com.bharath.college_management.service;

import com.bharath.college_management.entity.Department;
import com.bharath.college_management.entity.Student;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);
    List<Department> getAllDepartments();

    List<Department> sortBasedOnDepartmentName(String depName);

    List<Student> getAllStudentsUnderDepartment(String departmentId);

    Department getDepartmentById(String departmentId);

    Department updateDepartment(String departmentId, Department department);

    void deleteDepartment(String departmentId);

    List<Department> getDepartmentsInPages(int page, int size);
}

