package com.bharath.college_management.controller;

import com.bharath.college_management.entity.Student;
import com.bharath.college_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cm")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping(value = "s/get-all")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping(value = "s/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping(value = "s/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable String studentId) {
        Student foundStudent = studentService.getStudentById(studentId);
        return new ResponseEntity<>(foundStudent, HttpStatus.FOUND);
    }

    @GetMapping(value = "s/age/{age}")
    public ResponseEntity<List<Student>> getStudentsByAge(@PathVariable int age) {
        List<Student> students = studentService.getStudentByAge(age);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PutMapping("s/update/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable String studentId, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(studentId, student);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("s/delete/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("s/page")
    public ResponseEntity<List<Student>> getAllStudents(@RequestParam int page, @RequestParam int size) {
        List<Student> students = studentService.getStudentsInPages(page, size);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

}
