package com.bharath.college_management.controller;

import com.bharath.college_management.entity.Department;
import com.bharath.college_management.entity.Student;
import com.bharath.college_management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cm")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;


    @GetMapping(value = "get/d/get-all")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @PostMapping(value = "d/create")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping(value = "get/d/sort/{depName}")
    public ResponseEntity<List<Department>> getDepartmentsSortedByDepName(@PathVariable String depName) {
        List<Department> departments = departmentService.sortBasedOnDepartmentName(depName);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping(value = "d/students")
    public ResponseEntity<List<Student>> getAllStudentsInDepartment(@RequestParam String depId) {
        List<Student> students = departmentService.getAllStudentsUnderDepartment(depId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping(value = "d/{departmentId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable String departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PutMapping("d/update/{departmentId}")
    public ResponseEntity<Department> updateDepartment(@PathVariable String departmentId, @RequestBody Department department) {
        Department updatedDepartment = departmentService.updateDepartment(departmentId, department);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("d/delete/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String departmentId) {
        departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("d/page")
    public ResponseEntity<List<Department>> getDepartmentsInPages(@RequestParam int page, @RequestParam int size) {
        List<Department> departments = departmentService.getDepartmentsInPages(page, size);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}
