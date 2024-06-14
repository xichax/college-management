package com.bharath.college_management.controller;

import com.bharath.college_management.entity.Department;
import com.bharath.college_management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cm")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping(value = "d/get")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @PostMapping(value = "d/create")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping(value = "d/sort/{depName}")
    public ResponseEntity<List<Department>> getDepartmentsSortedByDepName(@PathVariable String depName) {
        List<Department> departments = departmentService.sortBasedOnDepartmentName(depName);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}
