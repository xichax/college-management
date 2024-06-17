package com.bharath.college_management.service.impl;

import com.bharath.college_management.Exceptions.DepartmentNotFoundException;
import com.bharath.college_management.entity.Department;
import com.bharath.college_management.entity.Student;
import com.bharath.college_management.repository.DepartmentRepository;
import com.bharath.college_management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public List<Student> getAllStudentsUnderDepartment(String departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            Department foundDepartment = department.get();
            if (foundDepartment.getStudents() != null) {
                return foundDepartment.getStudents();
            } else {
                return Collections.emptyList();
            }
        }
        else {
            throw new DepartmentNotFoundException("Department not found with ID: " + departmentId);
        }
    }

    public Department getDepartmentById(String departmentId) {
        Optional<Department> foundDepartment = departmentRepository.findByDepartmentId(departmentId);
        if (foundDepartment.isPresent()) {
            return foundDepartment.get();
        } else {
            throw new DepartmentNotFoundException("Department not found with ID: " + departmentId);
        }
    }

    public Department updateDepartment(String departmentId, Department department) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isPresent()) {
            Department existingDepartment = optionalDepartment.get();
            existingDepartment.setDepartmentName(department.getDepartmentName());
            existingDepartment.setAvailableOnline(department.getAvailableOnline());
            existingDepartment.setDepartmentId(department.getDepartmentId());
            existingDepartment.setCreatedAt(department.getCreatedAt());
            existingDepartment.setUpdatedAt(LocalDateTime.now());
            existingDepartment.setStudents(department.getStudents());
            existingDepartment.setTeachers(department.getTeachers());
            return departmentRepository.save(existingDepartment);
        } else {
            throw new DepartmentNotFoundException("Department not found with ID: " + departmentId);
        }
    }

    public void deleteDepartment(String departmentId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isPresent()) {
            departmentRepository.deleteById(departmentId);
        } else {
            throw new DepartmentNotFoundException("Department not found with ID: " + departmentId);
        }
    }

    public List<Department> getDepartmentsInPages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Department> departmentPage = departmentRepository.findAll(pageable);
        return departmentPage.getContent();
    }

}
